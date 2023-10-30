package com.example.cartmodule.model.domains;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private String street;

    private String city;

    private String zip;
}
