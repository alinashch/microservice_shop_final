package com.example.usermodule.service;

import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.model.dto.UserDTO;
import com.example.usermodule.model.entity.User;
import com.example.usermodule.model.request.UserCreateRequest;
import com.example.usermodule.model.request.UserUpdateRequest;
import com.example.usermodule.model.response.UserResponse;
import com.example.usermodule.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public void saveCustomer(UserCreateRequest userCreateRequest) {
        User customer = userMapper.toEntityFromRequest(userCreateRequest);
        userRepository.save(customer);

    }

    @Transactional
    public void updateCustomer(UserDTO userDTO, UserUpdateRequest userCreateRequest) {
        User entity  = userMapper.toEntityFromDTO(userDTO);
        userMapper.updateEntity(userCreateRequest, entity);
        userRepository.save(entity);
    }

    @Transactional
    public void deleteCustomer(String customerId) {
        userRepository.deleteById(customerId);
    }

    @Transactional
    public UserResponse findById(String customerId) {
        return userMapper.toResponseFromEntity(userRepository.findById(customerId).get());
    }

    public UserDTO getById(String customerId) {
        return userMapper.toDTOFromEntity(userRepository.findById(customerId).get());
    }


    @Transactional
    public List<UserResponse> findAll() {
        return userMapper.toUserResponseFromEntityList(userRepository.findAll());
    }

}
