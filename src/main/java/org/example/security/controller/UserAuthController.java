package org.example.security.controller;

import lombok.RequiredArgsConstructor;
import org.example.security.dto.AuthenticationRequest;
import org.example.security.dto.AuthenticationResponse;
import org.example.security.service.ConfigService;
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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(configService.login(request));
    }
}