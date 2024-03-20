package org.example.security.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.Exceptions;
import org.example.security.dto.AuthenticationRequest;
import org.example.security.dto.AuthenticationResponse;
import org.example.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final UserRepository userRepository;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            return AuthenticationResponse.builder()
                    .jwt(token)
                    .build();
        } catch (BadCredentialsException e) {
            throw new Exceptions("Credenciales inválidas.");
        }
    }
}