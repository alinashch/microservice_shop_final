package com.example.usermodule.mapper;


import com.example.usermodule.model.dto.UserDTO;
import com.example.usermodule.model.entity.User;
import com.example.usermodule.model.request.UserCreateRequest;
import com.example.usermodule.model.request.UserUpdateRequest;
import com.example.usermodule.model.response.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntityFromRequest(UserCreateRequest productCreateRequest);

    User toEntityFromDTO(UserDTO productDTO);

    UserDTO toDTOFromResponse(UserResponse productResponse);

    UserDTO toDTOFromEntity(User product);


    UserResponse toResponseFromEntity(User product);

    List<UserResponse> toUserResponseFromEntityList(List<User> productList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserUpdateRequest request, @MappingTarget User entity);

}
