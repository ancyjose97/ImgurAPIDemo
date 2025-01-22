package com.imgur.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF protection
            .authorizeHttpRequests()
            .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console without authentication
            .requestMatchers("/**").permitAll() // Allow all other endpoints without authentication
            .and()
            .formLogin().disable(); // Disable form login
        http.headers().frameOptions().sameOrigin(); // Allow frames to be loaded from the same origin (required for H2 console)
        return http.build();
    }
}
