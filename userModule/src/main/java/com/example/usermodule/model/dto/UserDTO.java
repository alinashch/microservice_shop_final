package com.example.usermodule.model.dto;

import com.example.usermodule.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String userId;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Address address;

    private LocalDate dateOfBirth;

}
