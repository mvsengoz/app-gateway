package com.gateway.app.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String user;
    private final String password;
    private final String role;

    public SecurityConfig(@Value(value = "${rest.api.auth.user}") String user,
                          @Value(value = "${rest.api.auth.password}") String password,
                          @Value(value = "${rest.api.auth.role}") String role) {

        this.user = user;
        this.password = password;
        this.role = role;
    }

    @Bean
    public UserDetailsService user(){

        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(user)
                .password(password)
                .roles(role)
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf-> csrf.disable()).authorizeHttpRequests((authorize) -> {
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }



}
