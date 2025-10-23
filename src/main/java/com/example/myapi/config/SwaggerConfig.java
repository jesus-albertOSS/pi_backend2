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

        // 🔐 Esquema de seguridad (Basic Auth)
        SecurityScheme basicAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("Autenticación básica con usuario y contraseña");

        SecurityRequirement basicAuthRequirement = new SecurityRequirement().addList("basicAuth");

        // 🌍 Servidores
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor local - desarrollo");

        Server renderServer = new Server()
                .url("https://pi-backend2-ru4x.onrender.com")
                .description("Servidor Render - producción");

        // ⚙️ Swagger Info
        Info apiInfo = new Info()
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
                        .url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .info(apiInfo)
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación técnica completa")
                        .url("https://docs.myapi.com"))
                .servers(List.of(localServer, renderServer))
                .components(new Components()
                        .addSecuritySchemes("basicAuth", basicAuthScheme))
                .addSecurityItem(basicAuthRequirement);
    }
}
