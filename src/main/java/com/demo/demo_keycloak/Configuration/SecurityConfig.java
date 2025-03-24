package com.demo.demo_keycloak.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/admin").hasRole("ADMIN") // Accès réservé aux admins
                .requestMatchers("/client").hasRole("CLIENT") // Accès réservé aux clients
                .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/", true) // Redirection après une authentification réussie
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Redirection après une déconnexion
            );
        return http.build();
    }
}
