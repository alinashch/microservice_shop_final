package com.example.productservice.configaration;


import com.example.productservice.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfiguration {
    @Bean
    public ProductMapper roleMapper() {
        return ProductMapper.INSTANCE;
    }


}