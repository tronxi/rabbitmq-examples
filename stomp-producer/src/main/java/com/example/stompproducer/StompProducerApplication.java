package com.example.stompproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StompProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StompProducerApplication.class, args);
    }

}
