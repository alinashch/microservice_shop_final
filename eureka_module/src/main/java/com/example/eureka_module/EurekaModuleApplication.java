package com.example.eureka_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaModuleApplication.class, args);
    }

}
