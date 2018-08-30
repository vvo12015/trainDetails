package net.vrakin.service;

import net.vrakin.model.Order;
import net.vrakin.model.OrderState;

import java.util.List;
import java.util.Optional;

public interface OrderStateService {

    Optional<OrderState> findByName(String name);
    OrderState getWaitState();
}
