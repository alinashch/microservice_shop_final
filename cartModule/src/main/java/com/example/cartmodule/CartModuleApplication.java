package com.example.cartmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableFeignClients
@EnableDiscoveryClient
public class CartModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartModuleApplication.class, args);
    }

}
