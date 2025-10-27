package com.example.myapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // ✅ 1. Habilita CORS usando tu CorsConfig
            .cors(cors -> cors.configure(http))
            
            // ✅ 2. Desactiva CSRF (innecesario para APIs REST)
            .csrf(csrf -> csrf.disable())
            
            // ✅ 3. Define rutas públicas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/products/**",      // tu API pública
                    "/swagger-ui/**",        // interfaz Swagger
                    "/swagger-ui.html", 
                    "/v3/api-docs/**", 
                    "/swagger-resources/**", 
                    "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated() // el resto requiere login
            )
            
            // ✅ 4. Usa autenticación básica (solo si es necesaria)
            .httpBasic();

        return http.build();
    }

    // ✅ Servicio de usuarios en memoria (solo para pruebas locales o Swagger)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    // ✅ Encriptador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
