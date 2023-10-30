package com.example.ordermodule.controller;

import com.example.ordermodule.model.domains.Cart;
import com.example.ordermodule.model.domains.User;
import com.example.ordermodule.model.entity.Order;
import com.example.ordermodule.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/placeOrder/orderNumber/{orderNumber}")
    public void placeOrder(@PathVariable Long orderNumber , @RequestBody User customer){
        orderService.placeOrder(orderNumber , customer);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getOrders(){
        return new ResponseEntity<List<Order>>(orderService.getOrders(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody List<Cart> cartLines){
        Order order = orderService.createOrder(cartLines);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
