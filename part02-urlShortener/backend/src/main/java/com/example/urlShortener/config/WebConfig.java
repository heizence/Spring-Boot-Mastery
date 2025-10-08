package com.example.urlShortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indicates that this class contains configuration settings.
public class WebConfig implements WebMvcConfigurer {

    // Injects the value from application.properties into this field.
    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    /**
     * Configures global CORS settings for the application.
     *
     * @param registry The CORS registry to which the configuration is added.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS settings to all paths under /api/
                .allowedOrigins(allowedOrigins) // Use the value from application.properties
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allowed request headers
                .allowCredentials(true); // Allow credentials (e.g., cookies)
    }
}