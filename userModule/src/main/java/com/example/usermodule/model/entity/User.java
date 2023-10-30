package com.example.usermodule.model.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Address address;

    private LocalDate dateOfBirth;

}
