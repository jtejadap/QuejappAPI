package com.example.quejapp;

import com.example.quejapp.model.Rol;
import com.example.quejapp.services.CustomUserDetailsService;
import com.example.quejapp.util.CustomAccessDeniedHandler;
import com.example.quejapp.util.CustomAuthenticationSuccesHandler;
import org.apache.coyote.Adapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    public String[] pathsToStaticResources = {
            "/resources/**",
            "/static/**",
            "/css/**",
            "/assets/**",
            "/vendor/**",
            "/fonts/**",
            "/static/favicon.ico",
            "/favicon.ico"
    };

    public String[] pathsToStaticRoutes = {
            "/",
            "/home",
            "/signup",
            "/access-denied"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").hasAnyRole(Rol.USER.name())
                        .requestMatchers("/attendant/**").hasRole(Rol.ADMINISTRATOR.name())
                        .requestMatchers(pathsToStaticRoutes).permitAll()
                        .requestMatchers(pathsToStaticResources).permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new CustomAuthenticationSuccesHandler())
                        .permitAll()
                )
                .exceptionHandling(ex->ex.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomAuthenticationSuccesHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
