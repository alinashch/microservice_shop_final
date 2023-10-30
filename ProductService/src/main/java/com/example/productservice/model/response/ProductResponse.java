package com.example.productservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private String productId;

    private String productName;

    private Long productPrice;

    private String productDescription;

    private String manufacturer;

    private Integer productNumInStock;

    private LocalDate dateOfCreate;

    private LocalDate dateOfExpiration;

}
