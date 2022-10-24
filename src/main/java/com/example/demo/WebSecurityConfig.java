package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> authz
        .requestMatchers("/api/**").permitAll()
        .anyRequest().authenticated())
        .oauth2Login()
        .and()
        .cors(withDefaults())
        .exceptionHandling()
        .defaultAuthenticationEntryPointFor(ajaxAuthenticationEntryPoint(), ajaxRequestMatcher())
        .and()
        .exceptionHandling()
        .defaultAuthenticationEntryPointFor(ajaxAuthenticationEntryPoint(), axiosRequestMatcher());
    return http.build();
  }

  @Bean
  public AuthenticationEntryPoint ajaxAuthenticationEntryPoint() {
    return new AuthenticationEntryPoint() {

      @Override
      public void commence(
          HttpServletRequest request,
          HttpServletResponse response,
          AuthenticationException authException)
          throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }
    };
  }

  @Bean
  public RequestMatcher ajaxRequestMatcher() {
    return new RequestHeaderRequestMatcher("X-Requested-With", "XMLHttpRequest");
  }

  @Bean
  public RequestMatcher axiosRequestMatcher() {
    return new RequestHeaderRequestMatcher("Sec-Fetch-Mode", "cors");
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers("/api/**");
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    corsConfiguration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", corsConfiguration);

    return corsSource;
  }
}
