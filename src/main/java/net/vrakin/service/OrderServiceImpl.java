package net.vrakin.service;

import net.vrakin.model.*;
import net.vrakin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl extends GeneralAbstractService<Order> implements  OrderService {

    public static final boolean IN_MOTION = true;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void init() {
        this.repo = orderRepository;
    }

    @Override
    public void delete(List<Order> orders) {
        orders.forEach(this::delete);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private OrderStateService orderStateService;

    @Autowired
    private CargoService cargoService;

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
    @Transactional
    public void refreshUserOrders(User user){
        orderRepository.refresh_orders();
        trainService.findByUser(user).forEach(t-> {
            if (orderRepository.findNotGenerationOrders(t.getId()).size() > 0){
                orderMaker.makeUserOrders(user);
            }else if (findByTrainWaiting(t).size() < orderGenerator.getCarCountMax()){
                orderGenerator.generateOrders(t);
            }
        });

    }

    @Override
    @Transactional
    public void refreshTrainOrders(Train train) {
        orderRepository.refresh_orders();
        if (orderRepository.findNotGenerationOrders(train.getId()).size() > 0){
            orderMaker.makeTrainOrders(train);
        }else if (findByTrainWaiting(train).size() < orderGenerator.getCarCountMax()){
            orderGenerator.generateOrders(train);
        }
    }

    private List<Order> findByTrainAndState(Train train, OrderState state) {
        return orderRepository.findByTrainAndState(train, state);
    }

    @Override
    public List<Order> findByTrainAndState(Train train, String orderStateName) {
        OrderState state = orderStateService.findByName(orderStateName);
        return orderRepository.findByTrainAndState(train, state);
    }

    @Override
    public List<Order> findByTrain(Train train) {
        return orderRepository.findByTrain(train);
    }

    @Override
    @Transactional
    public void startOrder(Order order) {
        orderRepository.start_order(order.getId(), order.getTrain().getId());
        entityManager.refresh(order);
    }

    @Override
    @Transactional
    public void finishOrder(Order order) {
        if (order.getState().getName().equals(OrderStateName.DONE.get())){
            Train train = order.getTrain();
            train.setCorpsState(trainService.checkTechnicalStatus(train));
            trainService.save(train);

            Company company = train.getCompany();

            Float cash = company.getCash();
            company.setCash(cash + order.getProfit());
            companyService.save(company);

            delete(order);
        }
    }

    @Override
    public List<Order> findByTrainAndStateIn(Train train, List<OrderState> states) {

        return orderRepository.findByTrainAndStateIn(train, states);
    }

    @Override
    public List<Order> findByNotGenerationOrders(Train train) {
        return orderRepository.findNotGenerationOrders(train.getId());
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }
}
