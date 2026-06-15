package com.example.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.example.presentation",
        "com.example.application",
        "com.example.infrastructure",
        "com.example.domain"
})
@EnableJpaRepositories(basePackages = "com.example.infrastructure.Repositories")
@EntityScan(basePackages = "com.example.infrastructure.jpa")
public class PresentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentationApplication.class, args);
    }
}