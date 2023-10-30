package com.example.ordermodule.model.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Product product;

    private Integer quantity;

    private void changeQuantity(Integer quantity){
        this.quantity = quantity;
    }

}
