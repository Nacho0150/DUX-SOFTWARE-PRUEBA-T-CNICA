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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

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
    protected AuthenticationResponse response;
    protected AuthenticationRequest authenticationRequest;
    protected String jwt;

    @BeforeEach
    protected void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        //LOGIN REQUEST
        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("test");
        authenticationRequest.setPassword("12345");
        //USER ENTITY
        user = new UserEntity();
        user.setId(1L);
        user.setUsername("test");
        jwt = jwtUtil.getToken(user);
        //RESPONSE
//        response = new AuthenticationResponse();
//        response.setJwt("token");
    }
}