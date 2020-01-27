package com.rudy.bibliotheque.mbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigurationProperties
@RefreshScope
@EnableDiscoveryClient
public class MicroserviceBookApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(MicroserviceBookApplication.class, args);
    }
}
