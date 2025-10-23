package com.example.myapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // 🔐 Autenticación actual: Basic Auth (coincide con tu SecurityConfig)
        SecurityScheme basicAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("Autenticación básica con usuario y contraseña");

        SecurityRequirement basicAuthRequirement = new SecurityRequirement().addList("basicAuth");

        // 🌍 Servidores (local y producción)
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor local");

        Server prodServer = new Server()
                .url("https://api.myapi.com")
                .description("Servidor de producción");

        // 📘 Configuración principal
        return new OpenAPI()
                .info(new Info()
                        .title("MyAPI - Documentación REST")
                        .version("1.0.0")
                        .description("""
                            API profesional para la gestión de usuarios, productos y operaciones internas.

                            Incluye endpoints CRUD, seguridad básica y base lista para JWT futuro.
                            """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@myapi.com")
                                .url("https://myapi.com"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación técnica completa")
                        .url("https://docs.myapi.com"))
                .servers(List.of(localServer, prodServer))
                .components(new Components().addSecuritySchemes("basicAuth", basicAuthScheme))
                .addSecurityItem(basicAuthRequirement);
    }
}
