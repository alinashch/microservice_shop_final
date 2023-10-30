package com.example.productservice.mapper;

import com.example.productservice.model.dto.ProductDTO;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.request.ProductCreateRequest;
import com.example.productservice.model.request.ProductUpdateRequest;
import com.example.productservice.model.response.ProductResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntityFromRequest(ProductCreateRequest productCreateRequest);

    Product toEntityFromDTO(ProductDTO productDTO);

    ProductDTO toDTOFromResponse(ProductResponse productResponse);

    ProductDTO toDTOFromEntity(Product product);


    ProductResponse toResponseFromEntity(Product product);

    List<ProductResponse> toProductResponseFromEntityList(List<Product> productList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductUpdateRequest request, @MappingTarget Product entity);

}
