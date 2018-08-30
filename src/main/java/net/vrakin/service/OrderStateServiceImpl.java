package net.vrakin.service;

import net.vrakin.model.OrderState;
import net.vrakin.repository.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStateServiceImpl implements OrderStateService {

    public static final String WAIT_STATE_NAME = "waiting";
    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    public Optional<OrderState> findByName(String name) {
        return orderStateRepository.findByName(name);
    }

    @Override
    public OrderState getWaitState() {
        return findByName(WAIT_STATE_NAME).get();
    }
}
