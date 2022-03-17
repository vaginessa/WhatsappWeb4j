package it.auties.whatsapp.model.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
@Builder
@Accessors(fluent = true)
public final class WebNotificationsInfo implements Info {
  @JsonProperty("2")
  @JsonPropertyDescription("uint64")
  private long timestamp;

  @JsonProperty("3")
  @JsonPropertyDescription("uint32")
  private int unreadChats;

  @JsonProperty("4")
  @JsonPropertyDescription("uint32")
  private int notifyMessageCount;

  @JsonProperty("5")
  @JsonPropertyDescription("WebMessageInfo")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<MessageInfo> notifyMessages;
}