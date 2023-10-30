package com.example.configuration_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationModuleApplication.class, args);
    }

}
