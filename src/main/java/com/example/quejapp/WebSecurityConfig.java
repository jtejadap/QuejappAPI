package com.example.quejapp;

import com.example.quejapp.model.Rol;
import com.example.quejapp.services.CustomUserDetailsService;
import com.example.quejapp.util.CustomAuthenticationSuccesHandler;
import org.apache.coyote.Adapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            "/fonts/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/complaint/**").hasAnyRole(Rol.USER.name(), Rol.ADMINISTRATOR.name(), Rol.MODERATOR.name())
                        .requestMatchers("/user/**").hasAnyRole(Rol.USER.name())
                        .requestMatchers("/attendant/**").hasRole(Rol.ADMINISTRATOR.name())
                        .requestMatchers("/moderator/**").hasRole(Rol.MODERATOR.name())
                        .requestMatchers("/","/home","/signup").permitAll()
                        .requestMatchers(pathsToStaticResources).permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new CustomAuthenticationSuccesHandler())
                        .permitAll()
                )
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
}
