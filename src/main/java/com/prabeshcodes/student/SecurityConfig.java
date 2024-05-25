package com.prabeshcodes.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.disable());

        return http.build();
    }
}