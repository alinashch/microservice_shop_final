package com.example.usermodule.model.response;

import com.example.usermodule.model.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {

    private String userId;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Address address;

    private LocalDate dateOfBirth;

}
