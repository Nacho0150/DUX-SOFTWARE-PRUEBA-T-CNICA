package org.example.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    @Schema(name = "username", example = "Ignacio", type = "String")
    private String username;

    @Schema(name = "password", example = "12345678", type = "String")
    private String password;
}