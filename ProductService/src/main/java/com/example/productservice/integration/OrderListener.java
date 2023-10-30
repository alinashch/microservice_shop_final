package com.example.productservice.integration;


import com.example.productservice.model.domain.OrderLine;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderListener {

    private final ProductService productService;

    @KafkaListener(topics = {"placeOrderTopic"})
    public void listenWhenOrderPlaced(@Payload String orderLinesString) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Message message = objectMapper.readValue(orderLinesString, Message.class);
            if (message.getCommand().equals("productService")) {
                System.out.println("Listen ....");
                Message<List<OrderLine>> messageOrderLines = objectMapper.readValue(
                        orderLinesString,
                        new TypeReference<Message<List<OrderLine>>>() {
                        });
                productService.removeQuantityOfProducts(messageOrderLines.getMessage());

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
