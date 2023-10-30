package com.example.productservice.service;

import com.example.productservice.exception.ProductNotfoundException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.domain.OrderLine;
import com.example.productservice.model.dto.ProductDTO;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.request.ProductCreateRequest;
import com.example.productservice.model.request.ProductUpdateRequest;
import com.example.productservice.model.response.ProductResponse;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Transactional
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productList = productMapper.toProductResponseFromEntityList(productRepository.findAll());
        return productList;
    }

    @Transactional
    public ProductDTO getProductById(String productNumber) {
        ProductDTO product = productMapper.toDTOFromEntity(productRepository.findById(productNumber).get());
        return product;
    }

    @Transactional
    public void addProduct(ProductCreateRequest productCreateRequest) {
        Product product = productMapper.toEntityFromRequest(productCreateRequest);
        productRepository.save(product);
    }

    @Transactional
    public void deleteProductById(String productNumber) {
        Product product = productRepository.findById(productNumber).orElse(null);
        if (product == null) {
            throw new ProductNotfoundException("Product not found");
        }
        productRepository.deleteById(productNumber);
    }

    @Transactional
    public void editProduct(ProductDTO productDTO, ProductUpdateRequest productCreateRequest) {
        Product entity  = productMapper.toEntityFromDTO(productDTO);
        productMapper.updateEntity(productCreateRequest, entity);
        productRepository.save(entity);
    }

    @Transactional
    public Integer getProductNumInStock(String productNumber) {
        Product product = productRepository.findById(productNumber).orElse(null);
        if (product == null) {
            return 0;
        }
        return product.getProductNumInStock();
    }

    @Transactional
    public void addProductToStock(String productNumber, Integer quantity) {
        Product product = productRepository.findById(productNumber).orElse(null);
        if (product == null) {
            throw new ProductNotfoundException("Product not found"); //handle exception
        }
        product.setProductNumInStock(product.getProductNumInStock() + quantity);
        productRepository.save(product);
    }

    @Transactional
    public void removeProductFromStock(String productNumber, Integer quantity) {
        Product product = productRepository.findById(productNumber).orElse(null);
        if (product == null) {
            throw new ProductNotfoundException("Not enough products in stock");
        }
        Integer productNumInStock = product.getProductNumInStock();
        if (quantity > productNumInStock) {
            throw new ProductNotfoundException("Not enough products in stock");
        }
        product.setProductNumInStock(productNumInStock - quantity);
        if (product.getProductNumInStock() == 0) {
            productRepository.delete(product);
        }
         productRepository.save(product);
    }

    public void removeQuantityOfProducts(List<OrderLine> orderLines) {
        if (orderLines==null) return;
        orderLines.stream().forEach(orderLine->{
            removeProductFromStock(orderLine.getProduct().getProductId(), orderLine.getQuantity());
        });
    }

}
