package com.rudy.bibliotheque.webui.config;

import com.rudy.bibliotheque.webui.exception.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    AuthInterceptor authFeign() {
        return new AuthInterceptor();
    }

    @Bean
    public CustomErrorDecoder customErrorDecoder(){
        return new CustomErrorDecoder();
    }
}
