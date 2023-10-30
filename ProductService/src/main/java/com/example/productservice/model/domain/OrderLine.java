package com.example.productservice.model.domain;

import com.example.productservice.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {

    private Product product;
    private Integer quantity;
}
