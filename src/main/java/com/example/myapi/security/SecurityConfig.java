package com.example.myapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitamos CSRF para APIs REST
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/items", "/api/items/{id}").permitAll()
    .requestMatchers("/api/products", "/api/products/**").permitAll()
    .anyRequest().authenticated()
)
            // Autenticación HTTP Basic (para Postman, navegador, etc.)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
