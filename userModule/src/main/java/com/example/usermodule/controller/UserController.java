package com.example.usermodule.controller;


import com.example.usermodule.model.dto.UserDTO;
import com.example.usermodule.model.request.UserCreateRequest;
import com.example.usermodule.model.request.UserUpdateRequest;
import com.example.usermodule.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody UserCreateRequest customer) {
        userService.saveCustomer(customer);
        return ResponseBuilder.build(CREATED, customer );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateCustomer(@PathVariable String userId, @RequestBody UserUpdateRequest customer) {
        UserDTO productDTO=userService.getById(userId);
        userService.updateCustomer(productDTO, customer);
        return ResponseBuilder.buildWithoutBodyResponse(OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String userId) {
        userService.deleteCustomer(userId);
        return ResponseBuilder.buildWithoutBodyResponse(NO_CONTENT);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<?> findById(@PathVariable String userId) {
        return ResponseBuilder.build(OK, userService.findById(userId) );
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findAll() {
        return ResponseBuilder.build(OK, userService.findAll() );
    }
}
