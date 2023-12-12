package com.example.mock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sms {
    private String sender;
    private String recipient;
    private String message;

    @JsonProperty("sender")
    public String getSender() {
        return sender;
    }

    @JsonProperty("recipient")
    public String getRecipient() {
        return recipient;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}
