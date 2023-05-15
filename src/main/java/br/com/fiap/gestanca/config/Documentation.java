package br.com.fiap.gestanca.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class Documentation {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .components(new Components()
        .addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT")));
    }
}
