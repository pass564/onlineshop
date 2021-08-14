package org.example.service.impl;

import org.example.dao.OrderDao;
import org.example.pojo.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Order> findAll() {
        return orderDao.selectAll();
    }

    @Override
    public void add(Order order) {
        orderDao.insert(order);
    }
}
