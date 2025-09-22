package com.michelegrieco.taskly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    /**
     * Configure security filter chain.
     * Allow access to H2 console and permit all other requests.
     * @param http
     * @return
     * @throws Exception
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().permitAll() // Allow all other requests
            )
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .csrf(crsf -> crsf.disable()) // Disable CSRF for H2 console access
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())); // Allow H2 console to be displayed in a frame

        return http.build();
    }

    @Bean
    /**
     * Create an in-memory user details service with a test user.
     * @param passwordEncoder
     * @return
     */
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {        
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    /**
     * Password encoder bean using BCrypt.
     * @return
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
