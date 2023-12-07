package com.messaginapps.simplemessaging;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.messaginapps.simplemessaging.repository")
public class SimpleMessagingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleMessagingApplication.class, args);
    }

}
