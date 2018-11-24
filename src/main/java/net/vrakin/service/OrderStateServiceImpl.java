package net.vrakin.service;

import net.vrakin.model.OrderState;
import net.vrakin.model.OrderStateName;
import net.vrakin.repository.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderStateServiceImpl implements OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    public OrderState findByName(String name) {
        return orderStateRepository.findByName(name).get();
    }

    @Override
    public OrderState getWaitState() {
        return findByName(OrderStateName.WAITING.get());
    }

    @Override
    public List<OrderState> findByNames(List<String> names) {
        return names.stream().map(
                n->
                {
                    return findByName(n);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderState> findByInMotion(boolean inMotion) {
        return orderStateRepository.findByInMotion(inMotion);
    }

    @Override
    public OrderState findById(Long id) {
        return orderStateRepository.findById(id).get();
    }

    @Override
    public List<OrderState> findAll() {
        return orderStateRepository.findAll();
    }

    @Override
    public void save(OrderState object) {
        orderStateRepository.save(object);
    }

    @Override
    public void delete(OrderState object) {
        orderStateRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(OrderState::toMap).collect(Collectors.toList());
    }

    @Override
    public boolean checkUniqueName(String name) {
        return orderStateRepository.findByName(name).isPresent();
    }
}
