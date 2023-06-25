package com.navi.imbd.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info( new Info().title("IMBD REST API clone")
                .description("CRUD on movies, Platforms,Reviews and Movie productions.")
                .version("0.0.1"));
    }
}
