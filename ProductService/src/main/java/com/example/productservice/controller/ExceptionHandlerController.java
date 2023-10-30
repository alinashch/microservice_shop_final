package com.example.productservice.controller;

import com.example.productservice.exception.ProductNotfoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(BindException e) {
        return ResponseBuilder.build(BAD_REQUEST,
                e.getBindingResult().getAllErrors().stream()
                        .map(error -> (FieldError) error)
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                (message1, message2) -> message1 + ", " + message2
                        )));
    }

    @ExceptionHandler({ProductNotfoundException.class})
    public ResponseEntity<?> notFoundExceptionHandler(Exception e) {
        return ResponseBuilder.build(NOT_FOUND, e);
    }
}