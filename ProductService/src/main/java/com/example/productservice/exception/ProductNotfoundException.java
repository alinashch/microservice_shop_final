package com.example.productservice.exception;

public class ProductNotfoundException extends RuntimeException {

    public ProductNotfoundException(String message) {
        super(message);
    }
}
