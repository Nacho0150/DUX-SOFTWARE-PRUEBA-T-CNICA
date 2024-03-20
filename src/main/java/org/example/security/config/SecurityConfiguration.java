package org.example.security.config;

import lombok.RequiredArgsConstructor;
import org.example.security.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**", "/auth/login").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers(
                                        "/api/docs/**",
                                        "/v1/api/get-token",
                                        "/api/doc/**",
                                        "/api/doc/swagger-ui.html/**",
                                        "/doc/swagger-ui.html/**",
                                        "/api/doc/swagger-ui-custom.html/**",
                                        "/api/docs/swagger-ui-custom.html/**",
                                        "/v3/api-docs/**",
                                        "/v3/api-doc/**",
                                        "/swagger-ui/**",
                                        "/configuration/ui",
                                        "/swagger-ui.html/**",
                                        "/swagger-resources/**",
                                        "/webjars/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions().disable()) // Deshabilitar X-Frame-Options
                .build();
    }
}