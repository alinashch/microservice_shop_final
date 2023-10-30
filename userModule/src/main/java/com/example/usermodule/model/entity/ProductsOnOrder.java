package com.example.usermodule.model.entity;


import com.example.usermodule.model.domains.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductsOnOrder {

    private Product product;

    private Integer quantity;

}
