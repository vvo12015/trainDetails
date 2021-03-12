package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.OrderStateNotFoundException;
import net.vrakin.model.City;
import net.vrakin.model.OrderState;
import net.vrakin.model.OrderStateName;
import net.vrakin.repository.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderStateServiceImpl extends GeneralAbstractService<OrderState> implements OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    protected void init() {
        log.debug("call method: init");

        this.repo = orderStateRepository;
    }

    @Override
    public OrderState findByName(String name) throws OrderStateNotFoundException {
        if (orderStateRepository.findByName(name).isPresent()){
            OrderState orderState = orderStateRepository.findByName(name).get();
            log.debug("call method: findByName with name: " + name + ". Found - " + orderState.getName());
        }
        log.debug("call method: findByName with name: " + name + ". No found");

        throw new OrderStateNotFoundException("Not found orderState with name: " + name);
    }

    @Override
    public OrderState getWaitState(){
        log.debug("call method: getWaitState");
        try {
            return findByName(OrderStateName.WAITING.get());
        } catch (OrderStateNotFoundException e) {
            throw new RuntimeException("-----Not found WaitState!!!!-----");
        }
    }

    @Override
    public List<OrderState> findByNames(List<String> names) {
        List<OrderState> orderStates = names.stream().map(n->{
            OrderState orderState = null;
            try{
                orderState = findByName(n);
            }catch (OrderStateNotFoundException e){
                log.error("-----Not found orderState with name: " + n + "!!!!-----");
            }
            return orderState;
        })
                .collect(Collectors.toList());
        log.debug("call method: findByNames with names. Received orderState count: " + orderStates.size());
        return orderStates;
    }

    @Override
    public List<OrderState> findByInMotion(boolean inMotion) {
        List<OrderState> orderStates = orderStateRepository.findByInMotion(inMotion);

        log.debug("call method: findByInMotion with inMotion: " + inMotion + ". Received orderState count: " + orderStates.size());
        return orderStates;
    }

    @Override
    public boolean checkUniqueName(String name) {

        boolean b = orderStateRepository.findByName(name).isPresent();

        log.debug("call method: checkUniqueName with name: " + name + ". Received value: " + b);

        return b;
    }
}
