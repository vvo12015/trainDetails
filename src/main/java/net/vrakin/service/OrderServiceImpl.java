package net.vrakin.service;

import net.vrakin.model.*;
import net.vrakin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    public static final boolean IN_MOTION = true;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private OrderStateService orderStateService;

    @Autowired
    private CargoSevice cargoSevice;

    @Autowired
    private RouteService routeService;

    @Autowired
    private OrderMaker orderMaker;

    @Autowired
    private OrderGenerator orderGenerator;

    @Override
    public List<Order> findByUser(User user) {
        Company company = companyService.findByUser(user).get(0);

        List<Train> trains = trainService.findByCompany(company);
        List<Order> orders = orderRepository.findByTrainIn(trains);
        return orders;
    }

    @Override
    public List<Order> findByTrainWaiting(Train train) {
        return findByTrainAndState(train, orderStateService.getWaitState());
    }

    @Override
    public Order findByTrainActionOrder(Train train) {

        List<OrderState> states = orderStateService.findByInMotion(IN_MOTION);
        List<Order> actionOrders = orderRepository.findByTrainAndStateIn(train, states);

        Order result = null;
        if (actionOrders.size() != 0) result = actionOrders.get(0);

        return result;
    }

    @Override
    public void refresh(User user){
        orderRepository.refresh_orders();
        orderMaker.makeOrders(user);
        orderGenerator.generateOrders(user);
    }

    @Override
    public void delete(List<Order> orders){
        orderRepository.deleteAll(orders);
    }

    private List<Order> findByTrainAndState(Train train, OrderState state) {
        return orderRepository.findByTrainAndState(train, state);
    }

    @Override
    public List<Order> findByTrainAndState(Train train, String orderStateName) {
        OrderState state = orderStateService.findByName(orderStateName).get(0);
        return orderRepository.findByTrainAndState(train, state);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findByTrain(Train train) {
        return orderRepository.findByTrain(train);
    }

    @Override
    public void startOrder(Order order) {

        String stateNameForStart = OrderStateName.DEADLINE1.get();

        OrderState stateForStart = orderStateService.findByName(stateNameForStart).get(0);

        order.setState(stateForStart);

        save(order);

        orderRepository.deleteAll(findByTrainWaiting(order.getTrain()));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void finishOrder(Order order) {
        if (order.getState().getName().equals(OrderStateName.DONE.get()) ||
                order.getState().getName().equals(OrderStateName.BELATED_DONE.get())){
            Train train = order.getTrain();
            train.setCorpsState(trainService.checkTechnicalStatus(train));
            trainService.save(train);

            Company company = train.getCompany();

            Float cash = company.getCash();
            company.setCash(cash + order.getProfit());
            companyService.save(company);

            delete(order);
            refresh(company.getUser());
        }
    }

    @Override
    public List<Map<String, String>> findAllToMap() {

        return findAll().stream().map(Order::toMap).collect(Collectors.toList());
    }
}
