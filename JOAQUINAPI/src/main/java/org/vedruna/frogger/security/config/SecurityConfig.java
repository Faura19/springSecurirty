package org.vedruna.frogger.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vedruna.frogger.security.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // 🔴 Desactivamos CSRF porque usamos JWT
                .authorizeHttpRequests(authReq ->
                        authReq
                                .requestMatchers("/api/v1/auth/**").permitAll() // 🔓 Permite acceso libre al login/registro
                                .requestMatchers("/redoc.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/v1/user/search").authenticated() // 🔓 Permite /search a usuarios autenticados
                                .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN", "USER") // 🔒 Solo USER o ADMIN
                                .requestMatchers("/api/v1/score/**").authenticated() // 🔓 Permite PUT, POST, DELETE y GET a usuarios autenticados
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}


