package org.example.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private String status;

    private int code;

    private String message;

    private List<String> errors;
}