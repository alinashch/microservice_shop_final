package com.example.usermodule.Integration;


import com.example.usermodule.model.entity.Order;
import com.example.usermodule.model.entity.ProductsOnOrder;
import com.example.usermodule.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Listener {

    @KafkaListener(topics = {"placeOrderTopic"})
    public void receive(String messageString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Message message = objectMapper.readValue(messageString,Message.class);
            if(message.getCommand().equals("customerService")){

                Message<Order> messageAddProductAndQuality = objectMapper.readValue(
                        messageString  ,
                        new TypeReference<Message<Order>>() {});

                User customer = messageAddProductAndQuality.getMessage().getUser();
                List<ProductsOnOrder> orderLines = messageAddProductAndQuality.getMessage().getProductsList();
                System.out.println("Order placed for this customer =" +customer +" is " + orderLines );

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
