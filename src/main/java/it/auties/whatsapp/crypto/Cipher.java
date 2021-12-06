package it.auties.whatsapp.crypto;

import io.netty.buffer.ByteBufUtil;
import it.auties.whatsapp.binary.BinaryArray;
import it.auties.whatsapp.binary.BinaryDecoder;
import it.auties.whatsapp.protobuf.key.IdentityKeyPair;
import it.auties.whatsapp.protobuf.model.Node;
import it.auties.whatsapp.protobuf.key.SignedKeyPair;
import it.auties.whatsapp.utils.Buffers;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.whispersystems.curve25519.Curve25519;
import org.whispersystems.libsignal.ecc.DjbECPrivateKey;
import org.whispersystems.libsignal.ecc.DjbECPublicKey;
import org.whispersystems.libsignal.util.KeyHelper;

import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * This utility class provides helper functionality to easily encrypt and decrypt data
 * This class should only be used for WhatsappWeb's WebSocket buffer operations
 *
 * TODO: Refactor and migrate off curve25519 library and decouple in multiple classes
 */
@UtilityClass
public class Cipher {
    private final BinaryDecoder DECODER = new BinaryDecoder();
    private final byte[] HANDSHAKE_PROLOGUE = new byte[]{87, 65, 5, 2};
    private final String CURVE_25519 = "X25519";
    private final String HMAC_SHA256 = "HmacSHA256";
    private final String SHA256 = "SHA-256";
    private final String SHA_PRNG = "SHA1PRNG";

    @SneakyThrows
    public IdentityKeyPair randomKeyPair() {
        var pair = KeyHelper.generateSenderSigningKey();
        var publicKey = ((DjbECPublicKey) pair.getPublicKey()).getPublicKey();
        var privateKey = ((DjbECPrivateKey) pair.getPrivateKey()).getPrivateKey();
        return new IdentityKeyPair(publicKey, privateKey);
    }

    @SneakyThrows
    public SignedKeyPair randomKeyPair(int id, @NonNull IdentityKeyPair identityKeyPair) {
        var encodedId = BinaryArray.of(id, 3).data();
        var keyPair = randomKeyPair();
        var publicKey = BinaryArray.of((byte) 5).append(keyPair.publicKey()).data();
        var signature = calculateSignature(identityKeyPair.privateKey(), publicKey);
        return new SignedKeyPair(encodedId, keyPair, signature);
    }

    @SneakyThrows
    public BinaryArray calculateSharedSecret(byte @NonNull [] publicKey, byte @NonNull [] privateKey) {
        var keyAgreement = KeyAgreement.getInstance(CURVE_25519);
        keyAgreement.init(toPKCS8Encoded(privateKey));
        keyAgreement.doPhase(toX509Encoded(publicKey), true);
        return BinaryArray.of(keyAgreement.generateSecret());
    }

    @SneakyThrows
    public boolean verifySignature(byte @NonNull [] publicKey, byte @NonNull [] message, byte @NonNull [] signature){
        return Curve25519.getInstance(Curve25519.BEST)
                .verifySignature(publicKey, message, signature);
    }

    @SneakyThrows
    public byte[] calculateSignature(byte @NonNull [] privateKey, byte @NonNull [] message) {
        return Curve25519.getInstance(Curve25519.BEST)
                .calculateSignature(privateKey, message);
    }

    @SneakyThrows
    public byte[] raw(@NonNull PublicKey publicKey) {
        return switch (PublicKeyFactory.createKey(publicKey.getEncoded())){
            case X25519PublicKeyParameters x25519 -> x25519.getEncoded();
            case Ed25519PublicKeyParameters ed25519 -> ed25519.getEncoded();
            default -> throw new IllegalStateException("Unsupported key type");
        };
    }

    @SneakyThrows
    public byte[] raw(@NonNull PrivateKey privateKey) {
        return switch (PrivateKeyFactory.createKey(privateKey.getEncoded())){
            case X25519PrivateKeyParameters x25519 -> x25519.getEncoded();
            case Ed25519PrivateKeyParameters ed25519 -> ed25519.getEncoded();
            default -> throw new IllegalStateException("Unsupported key type");
        };
    }

    @SneakyThrows
    public PublicKey toX509Encoded(byte @NonNull [] rawPublicKey) {
        var keyFactory = KeyFactory.getInstance(CURVE_25519);
        var publicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519), rawPublicKey);
        var publicKeySpec = new X509EncodedKeySpec(publicKeyInfo.getEncoded());
        return keyFactory.generatePublic(publicKeySpec);
    }

    @SneakyThrows
    public PrivateKey toPKCS8Encoded(byte @NonNull [] rawPrivateKey) {
        var keyFactory = KeyFactory.getInstance(CURVE_25519);
        var privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519), new DEROctetString(rawPrivateKey));
        var privateKey = new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded());
        return keyFactory.generatePrivate(privateKey);
    }

    @SneakyThrows
    public BinaryArray hmacSha256(@NonNull BinaryArray plain, @NonNull BinaryArray key) {
        var localMac = Mac.getInstance(HMAC_SHA256);
        localMac.init(new SecretKeySpec(key.data(), HMAC_SHA256));
        return BinaryArray.of(localMac.doFinal(plain.data()));
    }

    @SneakyThrows
    public byte[] sha256(byte @NonNull [] data) {
        var digest = MessageDigest.getInstance(SHA256);
        return digest.digest(data);
    }

    @SneakyThrows
    public BinaryArray sha256(@NonNull BinaryArray data) {
        return BinaryArray.of(sha256(data.data()));
    }

    public byte[] handshakePrologue(){
        return HANDSHAKE_PROLOGUE;
    }

    public Node decipherMessage(byte @NonNull [] message, @NonNull BinaryArray readKey, long iv){
        var aes = new AesGmc();
        aes.initialize(readKey.data(), null, iv, false);
        var plainText = aes.processBytes(message);
        return DECODER.decode(DECODER.unpack(plainText));
    }

    public BinaryArray cipherMessage(byte @NonNull [] message, BinaryArray writeKey, long iv, boolean prologue) {
        var ciphered = cipherMessage0(message, writeKey, iv);
        var buffer = ByteBufUtil.threadLocalDirectBuffer()
                .writeBytes(prologue ? handshakePrologue() : new byte[0])
                .writeInt(ciphered.length >> 16)
                .writeShort(65535 & ciphered.length)
                .writeBytes(ciphered);
        return Buffers.readBinary(buffer);
    }

    private byte[] cipherMessage0(byte[] message, BinaryArray writeKey, long iv) {
        if(writeKey == null){
            return message;
        }

        var aes = new AesGmc();
        aes.initialize(writeKey.data(), null, iv, true);
        return aes.processBytes(message);
    }

    @SneakyThrows
    public int randomRegistrationId() {
        var secureRandom = SecureRandom.getInstance(SHA_PRNG);
        return secureRandom.nextInt(16380) + 1;
    }

    @SneakyThrows
    public byte[] randomSenderKey() {
        var key = new byte[32];
        var secureRandom = SecureRandom.getInstance(SHA_PRNG);
        secureRandom.nextBytes(key);
        return key;
    }

    @SneakyThrows
    public int randomSenderKeyId() {
        var key = new byte[32];
        var secureRandom = SecureRandom.getInstance(SHA_PRNG);
        return secureRandom.nextInt(2147483647);
    }
}