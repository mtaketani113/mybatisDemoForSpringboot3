package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptor.AuthorizationHandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    AuthorizationHandlerInterceptor authorizationHandlerInterceptor() {
      return new AuthorizationHandlerInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/**")
              .allowedOrigins("http://localhost:3000")
              .allowedMethods("GET", "POST", "PUT", "DELETE")
              .allowedHeaders("Content-Type", "Authorization");
    }
  
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(authorizationHandlerInterceptor())
                     .addPathPatterns("/api/**");
    }
}