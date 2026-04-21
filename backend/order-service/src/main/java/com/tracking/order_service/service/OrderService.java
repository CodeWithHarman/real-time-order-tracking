package com.tracking.order_service.service;

import com.tracking.order_service.entity.Order;
import com.tracking.order_service.event.OrderEvent;
import com.tracking.order_service.kafka.OrderProducer;
import com.tracking.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderProducer producer;

    public OrderService(OrderRepository repository, OrderProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        Order saved = repository.save(order);

        producer.sendEvent(new OrderEvent(
                "ORDER_CREATED",
                saved.getId(),
                saved.getStatus()
        ));

        return saved;
    }

    public Optional<Order> getOrder(Long id) {
        return repository.findById(id);
    }

    public Order updateStatus(Long id, String status) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        Order updated = repository.save(order);

        producer.sendEvent(new OrderEvent(
                "ORDER_UPDATED",
                updated.getId(),
                updated.getStatus()
        ));

        return updated;
    }
}