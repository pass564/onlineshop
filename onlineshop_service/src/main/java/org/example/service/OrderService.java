package org.example.service;

import org.example.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    void add(Order order);

}
