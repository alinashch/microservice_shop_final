package com.example.productservice.model.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Getter
@Setter
public class Product {

    @Id

    private String productId;

    private String productName;

    private Long productPrice;

    private String productDescription;

    private String manufacturer;

    private Integer productNumInStock;

    private LocalDate dateOfCreate;

    private LocalDate dateOfExpiration;

}
