package com.example.myapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

        // 🔐 Esquema de seguridad (Autenticación básica)
        SecurityScheme basicAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("""
                        Este proyecto utiliza un sistema de autenticación básica
                        para proteger los endpoints de la API.
                        """);

        SecurityRequirement basicAuthRequirement = new SecurityRequirement().addList("basicAuth");

        // 🌍 Servidores disponibles
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor local para desarrollo y pruebas");

        Server renderServer = new Server()
                .url("https://pi-backend2-ru4x.onrender.com")
                .description("Servidor en producción - Render");

        // 🧾 Información general del proyecto
        Info apiInfo = new Info()
                .title("🎮 Game Connect - Documentación REST")
                .version("1.0.0")
                .description("""
                        Bienvenido a la **API REST de Game Connect**, un backend diseñado para 
                        la gestión de usuarios y productos de una plataforma de videojuegos.

                        ---
                        🔹 **Usuarios (`/users`)**
                        Permite registrar, autenticar y administrar usuarios dentro del sistema.

                        🔹 **Productos (`/products`)**
                        Gestiona los videojuegos disponibles, incluyendo su **nombre**, **descripción**, 
                        **disponibilidad**, **precio** e **imagen**.

                        ---
                        Esta documentación describe los endpoints disponibles, el modelo de seguridad 
                        y la estructura general del backend de Game Connect.
                        """);

        return new OpenAPI()
                .info(apiInfo)
                .externalDocs(new ExternalDocumentation()
                        .description("Guía técnica local para desarrolladores"))
                .servers(List.of(localServer, renderServer))
                .components(new Components()
                        .addSecuritySchemes("basicAuth", basicAuthScheme))
                .addSecurityItem(basicAuthRequirement);
    }
}
