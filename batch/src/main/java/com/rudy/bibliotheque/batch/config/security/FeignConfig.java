package com.rudy.bibliotheque.batch.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    AuthInterceptor authFeign() {
        return new AuthInterceptor();
    }
}
