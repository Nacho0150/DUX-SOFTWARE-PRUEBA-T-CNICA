package org.example.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageInfo {
    @JsonProperty("mensaje")
    private String message;
    @JsonProperty("codigo")
    private int statusCode;
}