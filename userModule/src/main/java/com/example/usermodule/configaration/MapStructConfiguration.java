package com.example.usermodule.configaration;


import com.example.usermodule.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfiguration {
    @Bean
    public UserMapper roleMapper() {
        return UserMapper.INSTANCE;
    }

}