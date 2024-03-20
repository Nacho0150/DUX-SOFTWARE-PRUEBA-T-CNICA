package org.example.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    @Schema(name = "jwt", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YXJhemFnYS5taWNrYWVsYUBnbWFpbC5jb20iLCJleHAiOjE2NDg3NzMyNTgsImlhdCI6MTY0ODczNzI1OH0.yA07ci-rUbAvfoIAB4jeqQwl8lg1NOabLLcCZFLZuis", type = "String")
    private String jwt;
}