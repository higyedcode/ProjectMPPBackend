package com.ubb.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/events/**") // Define the mapping path for which CORS should be enabled
                .allowedOrigins("http://localhost:3000", "https://localhost:3000", "https://toplanahead.netlify.app", "https://planahead-9vtd.onrender.com", "https://toplanahead.onrender.com") // Specify the allowed origin (frontend URL)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Specify the allowed HTTP methods
                .allowedHeaders("*"); // Specify the allowed headers
        registry.addMapping("/api/hosts/**") // Define the mapping path for which CORS should be enabled
                .allowedOrigins("http://localhost:3000", "https://localhost:3000", "https://toplanahead.netlify.app", "https://planahead-9vtd.onrender.com", "https://toplanahead.onrender.com") // Specify the allowed origin (frontend URL)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Specify the allowed HTTP methods
                .allowedHeaders("*"); // Specify the allowed headers


    }
}
