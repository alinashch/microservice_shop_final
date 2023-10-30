package com.example.cartmodule.model.entity;

import com.example.cartmodule.model.domains.User;
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
    private String orderNumber;
    private User customer;
    private List<ProductsOnOrder> orderLineList;

}
