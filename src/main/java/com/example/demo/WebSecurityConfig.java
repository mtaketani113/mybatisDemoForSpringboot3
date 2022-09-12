package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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

@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login()
        .and()
        .cors()
        .configurationSource(this.corsConfigurationSource())
        .and()
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
    return web -> web.ignoring().mvcMatchers("/api/**");
  }

  private CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedMethod("GET");
    corsConfiguration.addAllowedMethod("POST");
    corsConfiguration.addAllowedMethod("PUT");
    corsConfiguration.addAllowedMethod("DELETE");
    corsConfiguration.addAllowedHeader("Authorization");
    corsConfiguration.addAllowedHeader("Content-Type");
    corsConfiguration.addAllowedOrigin("http://localhost:3000");

    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", corsConfiguration);

    return corsSource;
  }
}
