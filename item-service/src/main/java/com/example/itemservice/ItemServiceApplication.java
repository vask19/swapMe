package com.example.itemservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableDiscoveryClient
@Slf4j
public class ItemServiceApplication {
    @Value("${acr.port}")
    private static int port;

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
        log.info("----Port{}------",port);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v1").allowedOrigins("http://localhost:"+port+"/*");
            }
        };
    }
}
