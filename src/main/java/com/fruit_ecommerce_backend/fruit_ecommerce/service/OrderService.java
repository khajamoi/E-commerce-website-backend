package com.fruit_ecommerce_backend.fruit_ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fruit_ecommerce_backend.fruit_ecommerce.entity.OrderEntity;
import com.fruit_ecommerce_backend.fruit_ecommerce.repository.OrderRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderEntity placeOrder(OrderEntity order) {
        order.setStatus("PENDING");
        return orderRepository.save(order);   // works
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();     // works
    }

    public Optional<OrderEntity> findById(Long id) {
        return orderRepository.findById(id);  // works
    }
}
