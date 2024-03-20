package org.example.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.exception.Exceptions;
import org.example.security.dto.AuthenticationRequest;
import org.example.security.dto.AuthenticationResponse;
import org.example.security.service.ConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final ConfigService configService;

    @Operation(summary = "Logeo del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Usuario logeado",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Error, credenciales inválidas", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(configService.login(request));
        } catch (Exceptions e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Autenticación fallida: " + e.getMessage());
        }
    }
}