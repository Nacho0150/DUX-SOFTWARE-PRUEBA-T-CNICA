package org.example.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Schema(name = "username", example = "Ignacio", type = "String")
    private String username;

    @Schema(name = "password", example = "12345678", type = "String")
    private String password;
}