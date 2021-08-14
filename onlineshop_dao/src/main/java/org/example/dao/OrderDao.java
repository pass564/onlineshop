package org.example.dao;

import org.example.pojo.Order;

import java.util.List;

public interface OrderDao {
    List<Order> selectAll();

    void insert(Order order);
}
