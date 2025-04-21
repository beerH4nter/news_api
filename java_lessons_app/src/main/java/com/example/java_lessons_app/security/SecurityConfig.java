package com.example.java_lessons_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Можно отключить CORS тут
                .csrf(CsrfConfigurer::disable) // Отключить CSRF (если не используешь сессии)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Разрешить все запросы
                );



        return http.build();
    }
}
