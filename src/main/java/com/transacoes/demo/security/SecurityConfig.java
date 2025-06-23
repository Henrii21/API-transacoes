package com.transacoes.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
            User.withUsername("admin")
                .password(encoder.encode("admin1903"))
                .roles("ADMIN")
                .build()
        );

        manager.createUser(
            User.withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build()
        );

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/transacao", "/transacao/cpf", "/transacao/nome").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/transacao/{id}", "/transacao", "/transacao/periodo").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {}) 
            .csrf(csrf -> csrf.disable()); 

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
