package com.michelegrieco.taskly.config;

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
            .csrf(crsf -> crsf.disable()) // Disable CSRF for H2 console access
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                .anyRequest().permitAll() // Allow all other requests
            ).headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())); // Allow H2 console to be displayed in a frame

        return http.build();
    }
}
