package com.example.ordermodule.Integration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {

    private String command;

    private T message;

}
