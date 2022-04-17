package it.auties.whatsapp;

import static it.auties.protobuf.api.model.ProtobufProperty.Type.*;

import it.auties.protobuf.api.model.ProtobufProperty;
import java.util.*;
import lombok.*;
import lombok.experimental.*;
import lombok.extern.jackson.*;

@AllArgsConstructor
@Data
@Builder
@Jacksonized
@Accessors(fluent = true)
public class Reaction {

  @ProtobufProperty(index = 1, type = MESSAGE, concreteType = MessageKey.class)
  private MessageKey key;

  @ProtobufProperty(index = 2, type = STRING)
  private String text;

  @ProtobufProperty(index = 3, type = STRING)
  private String groupingKey;

  @ProtobufProperty(index = 4, type = INT64)
  private long senderTimestampMs;

  @ProtobufProperty(index = 5, type = BOOL)
  private boolean unread;
}
