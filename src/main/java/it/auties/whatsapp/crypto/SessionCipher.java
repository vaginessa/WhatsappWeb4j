package it.auties.whatsapp.crypto;

import it.auties.whatsapp.binary.BinaryArray;
import it.auties.whatsapp.manager.WhatsappKeys;
import it.auties.whatsapp.protobuf.signal.keypair.SignalKeyPair;
import it.auties.whatsapp.protobuf.signal.keypair.SignalPreKeyPair;
import it.auties.whatsapp.protobuf.signal.message.SignalMessage;
import it.auties.whatsapp.protobuf.signal.message.SignalPreKeyMessage;
import it.auties.whatsapp.protobuf.signal.session.*;
import it.auties.whatsapp.util.Validate;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static java.util.Arrays.copyOfRange;
import static java.util.Objects.requireNonNull;

public record SessionCipher(@NonNull SessionAddress address, @NonNull WhatsappKeys keys) {
    @SneakyThrows
    public byte[] encrypt(byte[] data){
        var session = loadSession();
        Validate.isTrue(keys.hasTrust(address, session.currentState().remoteIdentityKey()),
                "Untrusted key", SecurityException.class);

        var chain = session.currentState()
                .findChain(session.currentState().ephemeralKeyPair().publicKey())
                .orElseThrow(() -> new NoSuchElementException("Missing chain for %s".formatted(address)));
        fillMessageKeys(chain, chain.counter() + 1);

        var currentKey = chain.messageKeys()
                .get(chain.counter())
                .publicKey();
        var whisperKeys = Hkdf.deriveSecrets(currentKey, "WhisperMessageKeys".getBytes(StandardCharsets.UTF_8));
        chain.messageKeys().remove(chain.counter());

        var encrypted = AesCbc.encrypt(copyOfRange(whisperKeys[2], 0, 16), data, whisperKeys[0]);
        var message = SignalMessage.builder()
                .ephemeralPublicKey(session.currentState().ephemeralKeyPair().publicKey())
                .counter(chain.counter())
                .previousCounter(session.currentState().previousCounter())
                .ciphertext(encrypted)
                .build()
                .serialized();

        var macStream = new ByteArrayOutputStream();
        macStream.write(SignalHelper.appendKeyHeader(keys.identityKeyPair().publicKey()));
        macStream.write(SignalHelper.appendKeyHeader(session.currentState().remoteIdentityKey()));
        macStream.write(message);

        var mac = Hmac.calculate(macStream.toByteArray(), whisperKeys[1]);
        var result = BinaryArray.of(message)
                .append(mac.cut(8))
                .data();
        keys.addSession(address, session);
        if(session.currentState().pendingPreKey() == null){
            return result;
        }

        return SignalPreKeyMessage.builder()
                .identityKey(keys.identityKeyPair().publicKey())
                .registrationId(keys.id())
                .baseKey(session.currentState().pendingPreKey().baseKey())
                .signedPreKeyId(session.currentState().pendingPreKey().signedPreKeyId())
                .preKeyId(session.currentState().pendingPreKey().preKeyId())
                .serializedSignalMessage(result)
                .build()
                .serialized();
    }

    //     fillMessageKeys(chain, counter) {
    //        if (chain.chainKey.counter >= counter) {
    //            return;
    //        }
    //        if (counter - chain.chainKey.counter > 2000) {
    //            throw new errors.SessionError('Over 2000 messages into the future!');
    //        }
    //        if (chain.chainKey.key === undefined) {
    //            throw new errors.SessionError('Chain closed');
    //        }
    //        const key = chain.chainKey.key;
    //        chain.messageKeys[chain.chainKey.counter + 1] = crypto.calculateMAC(key, Buffer.from([1]));
    //        chain.chainKey.key = crypto.calculateMAC(key, Buffer.from([2]));
    //        chain.chainKey.counter += 1;
    //        return this.fillMessageKeys(chain, counter);
    //    }
    private void fillMessageKeys(SessionChain chain, int counter) {
        if (chain.counter() >= counter) {
            return;
        }

        Validate.isTrue(counter - chain.counter() <= 2000,
                "Message overflow: expected <= 2000, got %s", counter - chain.counter());
        Validate.isTrue(chain.key() != null,
                "Closed chain");

        var messagesHmac = Hmac.calculate(new byte[]{1}, chain.key());
        var keyPair = new SignalPreKeyPair(chain.counter() + 1, messagesHmac.data(), null);
        chain.messageKeys().put(chain.counter() + 1, keyPair);

        var keyHmac = Hmac.calculate(new byte[]{2}, chain.key());
        chain.key(keyHmac.data());
        chain.incrementCounter();
        fillMessageKeys(chain, counter);
    }

    public byte[] decrypt(SignalMessage message) {
        var session = loadSession();
        for(var state : session.states()){
            try {
                Validate.isTrue(keys.hasTrust(address, state.remoteIdentityKey()),
                        "Untrusted key");
                var result = decrypt(message, state);
                keys.addSession(address, session);
                return result;
            }catch (Throwable ignored){

            }
        }

        throw new RuntimeException("Cannot decrypt message");
    }

    public byte[] decrypt(SignalPreKeyMessage message) {
        var session = loadSession(() -> {
            Validate.isTrue(message.registrationId() != 0, "Missing registration id");
            return new Session();
        });

        var builder = new SessionBuilder(address, keys);
        var preKeyId = builder.createIncoming(session, message);
        var state = session.findState(message.version(), message.baseKey())
                .orElseThrow(() -> new NoSuchElementException("Missing state"));
        var plaintext = decrypt(message.signalMessage(), state);
        keys.addSession(address, session);
        if(preKeyId != 0) {
            keys.preKeys().remove(preKeyId);
        }

        return plaintext;
    }

    @SneakyThrows
    private byte[] decrypt(SignalMessage message, SessionState state) {
        maybeStepRatchet(message, state);
        var chain = state.findChain(message.ephemeralPublicKey())
                .orElseThrow(() -> new NoSuchElementException("Invalid chain"));
        fillMessageKeys(chain, message.counter());
        Validate.isTrue(chain.hasMessageKey(message.counter()),
                "Key used already or never filled");
        var messageKey = chain.messageKeys().get(message.counter());
        chain.messageKeys().remove(message.counter());
        var secrets = Hkdf.deriveSecrets(messageKey.publicKey(),
                "WhisperMessageKeys".getBytes(StandardCharsets.UTF_8));
        var plaintext = AesCbc.decrypt(copyOfRange(secrets[2], 0, 16), message.ciphertext(), secrets[0]);
        state.pendingPreKey(null);
        return plaintext;
    }

    private void maybeStepRatchet(SignalMessage message, SessionState state) {
        if (state.hasChain(message.ephemeralPublicKey())) {
            return;
        }

        var previousRatchet = state.findChain(state.lastRemoteEphemeralKey());
        previousRatchet.ifPresent(chain -> {
            fillMessageKeys(chain, state.previousCounter());
            chain.key(null);
        });

        calculateRatchet(message, state, false);
        var previousCounter = state.findChain(state.ephemeralKeyPair().publicKey());
        previousCounter.ifPresent(chain -> {
            state.previousCounter(chain.counter());
            state.chains().remove(chain);
        });

        state.ephemeralKeyPair(SignalKeyPair.random());
        calculateRatchet(message, state, true);
        state.lastRemoteEphemeralKey(message.ephemeralPublicKey());
    }

    private void calculateRatchet(SignalMessage message, SessionState state, boolean sending) {
        var sharedSecret = Curve.calculateSharedSecret(message.ephemeralPublicKey(),
                state.ephemeralKeyPair().privateKey());
        var masterKey = Hkdf.deriveSecrets(sharedSecret.data(), state.rootKey(),
                "WhisperRatchet".getBytes(StandardCharsets.UTF_8), 2);
        var chainKey = sending ? state.ephemeralKeyPair().publicKey() : message.ephemeralPublicKey();
        state.addChain(new SessionChain(-1, masterKey[1], chainKey));
        state.rootKey(masterKey[0]);
    }

    private Session loadSession() {
        return loadSession(() -> null);
    }

    private Session loadSession(Supplier<Session> defaultSupplier) {
        return keys.findSessionByAddress(address)
                .orElseGet(() -> requireNonNull(defaultSupplier.get(), "Missing session for %s".formatted(address)));
    }
}