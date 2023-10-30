package com.example.ordermodule.model.entity;

import com.example.ordermodule.model.domains.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {

    @Id
    private String orderId;

    private User user;

    private List<ProductsOnOrder> orderLineList;

}
