package com.sisd.config;

import com.sisd.exception.CustomAccessDeniedException;
import com.sisd.exception.CustomUnAuthorizeException;
import com.sisd.security.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    JwtFilter jwtFilter;
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(ex -> ex
                .authenticationEntryPoint(new CustomUnAuthorizeException())
                .accessDeniedHandler(new CustomAccessDeniedException()))
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**",
                        "/student/register",
                        "/student/upload-student-photo",
                        "/student/upload-parent-photi",
                        "/v3/api-docs/**",
                        "/swagger-ui/**")
                .permitAll()
                .anyRequest().authenticated())
        .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
return http.build();
    }


}
