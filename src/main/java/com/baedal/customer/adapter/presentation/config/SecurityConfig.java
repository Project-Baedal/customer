package com.baedal.customer.adapter.presentation.config;

import com.baedal.customer.adapter.presentation.security.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())

        .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class)

        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/v0/login").permitAll()
            .requestMatchers("/v0/signup").permitAll()
            .requestMatchers("/error").permitAll()
            .anyRequest().hasRole("CUSTOMER"))
        .build();
  }

}