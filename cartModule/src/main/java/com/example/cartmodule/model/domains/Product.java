package com.example.cartmodule.model.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String productId;

    private String productName;

    private Long productPrice;

    private String productDescription;

    private String manufacturer;

    private Integer productNumInStock;

    private LocalDate dateOfCreate;

    private LocalDate dateOfExpiration;

}
