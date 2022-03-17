package it.auties.whatsapp.model.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import it.auties.bytes.Bytes;
import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.contact.ContactJid;
import it.auties.whatsapp.model.info.MessageInfo;
import lombok.*;
import lombok.Builder.Default;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import java.util.Locale;

/**
 * A container for unique identifiers and metadata linked to a {@link Message} and contained in {@link MessageInfo}.
 * This class is only a model, this means that changing its values will have no real effect on WhatsappWeb's servers.
 * Instead, methods inside {@link Whatsapp} should be used.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Accessors(fluent = true)
@Jacksonized
@Builder(builderMethodName = "newMessageKey", buildMethodName = "create")
public class MessageKey {
  /**
   * The jid of the contact or group that sent the message.
   */
  @JsonProperty("1")
  @JsonPropertyDescription("ContactJid")
  @NonNull
  private ContactJid chatJid;

  /**
   * Determines whether the message was sent by you or by someone else
   */
  @JsonProperty("2")
  @JsonPropertyDescription("bool")
  private boolean fromMe;

  /**
   * The jid of the message
   */
  @JsonProperty("3")
  @JsonPropertyDescription("string")
  @NonNull
  @Default
  private String id = Bytes.ofRandom(8)
          .toHex()
          .toUpperCase(Locale.ROOT);
}