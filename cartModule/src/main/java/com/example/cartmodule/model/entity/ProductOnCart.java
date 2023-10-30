package com.example.cartmodule.model.entity;

import com.example.cartmodule.model.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOnCart {

    private Product product;

    private Integer quantity;

    public void changeQuantity(Integer quantity){
        this.quantity = quantity;
    }


}
