package com.example.StreamOn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll() // Permitir acesso às rotas públicas
                        .requestMatchers(new AntPathRequestMatcher("/videos/**")).hasAnyRole("USER", "ADMIN") // Proteger rotas específicas
                        .requestMatchers(new AntPathRequestMatcher("/categories/**", "/users/**")).hasRole("ADMIN") // Apenas ADMIN acessa
                        .anyRequest().authenticated() // Outras rotas precisam de autenticação
                )
                .httpBasic(httpBasic -> {}); // Nova API para autenticação básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
