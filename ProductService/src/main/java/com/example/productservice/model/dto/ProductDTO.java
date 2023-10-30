package com.example.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String productId;

    private String productName;

    private Long productPrice;

    private String productDescription;

    private String manufacturer;

    private Integer productNumInStock;

    private LocalDate dateOfCreate;

    private LocalDate dateOfExpiration;

}
