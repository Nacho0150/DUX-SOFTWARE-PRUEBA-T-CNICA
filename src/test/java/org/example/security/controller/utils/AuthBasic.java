package org.example.security.controller.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.security.dto.AuthenticationRequest;
import org.example.security.dto.AuthenticationResponse;
import org.example.security.entity.UserEntity;
import org.example.security.service.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthBasic {

    @Autowired
    protected JwtUtils jwtUtil;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected UserEntity user;
    protected String jwt;

    @BeforeEach
    protected void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        // Crea un usuario
        user = new UserEntity();
        user.setId(1L);
        user.setUsername("test");
        // Genera el token JWT
        jwt = jwtUtil.getToken(user);
    }

    protected String obtainAccessToken(String username, String password) throws Exception {

        // Construye la solicitud de autenticación
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);

        // Enviar solicitud de autenticación al endpoint de login
        String responseString = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Parsea la respuesta para obtener el token JWT
        AuthenticationResponse authenticationResponse = objectMapper.readValue(responseString, AuthenticationResponse.class);
        return authenticationResponse.getJwt();
    }
}