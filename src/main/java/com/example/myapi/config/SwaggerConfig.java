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

        // 🔐 Autenticación básica
        SecurityScheme basicAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .name("basicAuth") // 👈 nombre explícito (buena práctica)
                .description("""
                        Este proyecto utiliza un sistema de autenticación básica (HTTP Basic Auth)
                        para proteger los endpoints de la API.
                        Incluye las credenciales en el encabezado Authorization.
                        """);

        SecurityRequirement basicAuthRequirement = new SecurityRequirement().addList("basicAuth");

        // 🌍 Servidores disponibles (puedes agregar más si usas otros entornos)
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor local (desarrollo)");

        Server renderServer = new Server()
                .url("https://pi-backend2-ru4x.onrender.com")
                .description("Servidor en producción - Render");

        // 🧾 Información general de la API
        Info apiInfo = new Info()
                .title("🎮 Game Connect - Documentación REST")
                .version("1.0.0")
                .description("""
                        Bienvenido a la **API REST de Game Connect** 🎮  
                        Esta API permite la gestión de usuarios y productos de una plataforma de videojuegos.

                        ---
                        **Endpoints principales:**
                        - `/api/users` → Registro, autenticación y administración de usuarios.  
                        - `/api/products` → Gestión de videojuegos: nombre, descripción, disponibilidad, precio e imagen.  
                        
                        ---
                        ⚙️ Autenticación:
                        Usa el esquema **Basic Auth** para acceder a los endpoints protegidos.
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
