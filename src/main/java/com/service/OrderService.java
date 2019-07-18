package com.service;

import com.model.Order;

import java.util.Optional;

public interface OrderService {

    void add(Order order);

    Optional<Order> getById(Long id);
}
