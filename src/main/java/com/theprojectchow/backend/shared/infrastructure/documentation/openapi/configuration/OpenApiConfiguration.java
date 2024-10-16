package com.theprojectchow.backend.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI tpcOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi.info(new Info()
                .title("The Project Chow API")
                .description("The Project Chow application REST API documentation.")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0")
                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ACME Learning Platform wiki Documentation")
                        .url("https://acme-learning-platform.wiki.github.io/docs"));
        return openApi;
    }
}
