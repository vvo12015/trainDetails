package net.vrakin.service;

import net.vrakin.model.Order;
import net.vrakin.model.OrderState;
import net.vrakin.model.Train;
import net.vrakin.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService extends GeneralService<Order>{

    List<Order> findByUser(User user);

    List<Order> findByTrainWaiting(Train train);

    Order findByTrainActionOrder(Train train);

    List<Order> findByTrainAndState(Train train, String orderStateName);

    List<Order>  findByTrainAndStateIn(Train train, List<OrderState> states);

    List<Order> findByNotGenerationOrders(Train train);

    void refresh(User user);

    List<Order> findByTrain(Train train);

    void startOrder(Order order);

    void finishOrder(Order order);

    void delete(Order order);

    void delete(List<Order> orders);
}
