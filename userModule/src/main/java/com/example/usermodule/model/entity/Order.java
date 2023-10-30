package com.example.usermodule.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String orderNumber;
    private User user;
    private List<ProductsOnOrder> productsList;


}
