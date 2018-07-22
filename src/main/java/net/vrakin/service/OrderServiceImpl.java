package net.vrakin.service;

import net.vrakin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void refresh() {

    }
}
