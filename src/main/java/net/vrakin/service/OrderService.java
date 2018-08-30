package net.vrakin.service;

import net.vrakin.model.Order;
import net.vrakin.model.User;

import java.util.List;

public interface OrderService {

    void refresh();

    List<Order> findByUser(User user);

    List<Order> findWaitOrderNow();
}
