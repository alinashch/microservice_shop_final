package com.example.productservice.controller;

import com.example.productservice.model.dto.ProductDTO;
import com.example.productservice.model.request.ProductCreateRequest;
import com.example.productservice.model.request.ProductUpdateRequest;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private static final Logger logger =
            LoggerFactory.getLogger(ProductController.class.getName());

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        logger.info("Calling get all Products");
        return ResponseBuilder.build(OK, productService.getAllProducts());
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<?> getProductById(@PathVariable("productNumber") String productNumber) {
        logger.info("Calling get product by id");
        return ResponseBuilder.build(OK, productService.getProductById(productNumber));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductCreateRequest product) {
        logger.info("Calling add product by id");
        productService.addProduct(product);
        return ResponseBuilder.buildWithoutBodyResponse(CREATED );
    }

    @DeleteMapping("/{productNumber}")
    public ResponseEntity<?> deleteProductById(@PathVariable("productNumber") String productNumber) {
        logger.info("Calling delete product by id");
        productService.deleteProductById(productNumber);
        return ResponseBuilder.buildWithoutBodyResponse(NO_CONTENT);
    }

    @PutMapping("/{productNumber}")
    public ResponseEntity<?> editProduct(@PathVariable String productNumber, @RequestBody ProductUpdateRequest productCreateRequest) {
        logger.info("Calling edit product by id");
        ProductDTO productDTO=productService.getProductById(productNumber);
        productService.editProduct(productDTO, productCreateRequest);
        return ResponseBuilder.buildWithoutBodyResponse(OK);
    }

    @GetMapping("/{productNumber}/isInStock")
    public ResponseEntity<?> productIsInStock(@PathVariable String productNumber) {
        logger.info("Calling get product in stock");
        if (productService.getProductNumInStock(productNumber) > 0) {
            return ResponseBuilder.build(OK, true);
        }
        return ResponseBuilder.build(OK, false);
    }


}
