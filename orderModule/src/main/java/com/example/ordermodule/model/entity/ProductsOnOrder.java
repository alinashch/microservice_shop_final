package com.example.ordermodule.model.entity;

import com.example.ordermodule.model.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOnOrder {

    private Product product;

    private Integer quantity;
}
