package com.example.ordermodule.service;

import com.example.ordermodule.Integration.Message;
import com.example.ordermodule.Integration.Sender;
import com.example.ordermodule.model.domains.Cart;
import com.example.ordermodule.model.domains.User;
import com.example.ordermodule.model.entity.Order;
import com.example.ordermodule.model.entity.ProductsOnOrder;
import com.example.ordermodule.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final Sender sender;


    @Transactional
    public List<Order> getOrders() {

        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(List<Cart> cartLines) {

        Order order = new Order();
        List orderLineList = new ArrayList<>();
        for (Cart cartLine : cartLines) {
            ProductsOnOrder orderLine = new ProductsOnOrder();
            orderLine.setProduct(cartLine.getProduct());
            orderLine.setQuantity(cartLine.getQuantity());
            orderLineList.add(orderLine);
        }

        order.setOrderLineList(orderLineList);
        Order order1 = orderRepository.save(order);
        return order1;
    }

    @Transactional
    public void placeOrder(Long orderNumber, User customer) {
        Order order = orderRepository.findByOrderId(orderNumber).get();
        System.out.println("1" + order);

        order.setUser(customer);
        System.out.println("2" + order);

        List<ProductsOnOrder> orderLines = new ArrayList<>(order.getOrderLineList());
        System.out.println("3" + order);

        orderRepository.save(order);

        Message<List<ProductsOnOrder>> messageOrderLines = new Message<List<ProductsOnOrder>>("productService", orderLines);

        Message<Order> messageOrder = new Message<Order>("customerService", order);

        System.out.println("1" + order);

        sender.send(messageOrderLines);

        System.out.println("This is the message to be send" + messageOrder);

        sender.send(messageOrder);
    }
}
