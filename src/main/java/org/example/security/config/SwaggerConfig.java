package org.example.security.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    private static final String TITLE = "DUX SOFTWARE | PRUEBA TÉCNICA";
    private static final String DESCRIPTION = "Documentación del proyecto de Ignacio Ibañez";

    @Bean
    public OpenAPI customOpenAPI(@Value("${project:version}") String appVersion) {
        OpenAPI openApi = new OpenAPI();
        openApi.info(
                new Info()
                        .title(TITLE)
                        .version(appVersion)
                        .description(DESCRIPTION)
        );

        openApi.components(
                new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name("Authorization"))
        );

        openApi.addSecurityItem(
                new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"))
        );

        return openApi;
    }
}