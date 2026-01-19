package com.desafiomoura.sistema_integrado_moura.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Ponto Eletrônico - Moura Tech")
                        .version("1.0")
                        .description("API REST para gerenciamento de ponto eletrônico de funcionários")
                        .contact(new Contact()
                                .name("Abner Barretto")
                                .email("abner@moura.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor Local")
                ));
    }
}