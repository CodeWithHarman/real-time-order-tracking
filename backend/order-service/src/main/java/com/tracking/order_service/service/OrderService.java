package com.tracking.order_service.service;

import com.tracking.order_service.entity.Order;
import com.tracking.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        return repository.save(order);
    }

    public Optional<Order> getOrder(Long id) {
        return repository.findById(id);
    }

    public Order updateStatus(Long id, String status) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return repository.save(order);
    }
}