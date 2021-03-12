package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.exception.OrderStateNotFoundException;
import net.vrakin.model.*;
import net.vrakin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl extends GeneralAbstractService<Order> implements  OrderService {

    public static final boolean IN_MOTION_ON = true;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void init() {
        log.debug("call method: init");

        this.repo = orderRepository;
    }

    @Override
    public void deleteOrderList(List<Order> orders) {
        log.debug("call method: deleteOrderList with list size: " + orders.size());
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
    public List<Order> findByUser(User user) throws CompanyNotFoundException {
        if (companyService.findByUser(user).size() > 0) {
            Company company = companyService.findByUser(user).get(0);

            List<Train> trains = trainService.findByCompany(company);
            List<Order> orders = orderRepository.findByTrainIn(trains);
            log.debug("call method: findByUser with user: " + user.getUsername() +
                    ". Found orders count " + orders.size());
            return orders;
        }
        throw new CompanyNotFoundException("call method findByUser with user: " + user.getName() + " hasn't company.");
    }

    @Override
    public List<Order> findByTrainWaiting(Train train) {
        List<Order> orders = findByTrainAndState(train, orderStateService.getWaitState());
        log.debug("call method findByTrainWaiting with train: " + train.getName() + ". Received order count: " + orders.size());
        return orders;
    }

    @Override
    public Order findByTrainActionOrder(Train train) {

        List<OrderState> states = orderStateService.findByInMotion(IN_MOTION_ON);
        List<Order> actionOrders = orderRepository.findByTrainAndStateIn(train, states);

        Order result = null;
        if (actionOrders.size() != 0) result = actionOrders.get(0);

        String resultLog = result!=null?(result.getName()):("null");

        log.debug("call method: findByTrainActionOrder with train: " + train.getName() + " actionOrder count: " + actionOrders.size()
                + ". Received result (zero active order): " + resultLog);

        return result;
    }

    @Override
    @Transactional
    public void refreshUserOrders(User user){
        log.debug("call method: refreshUserOrders with user: " + user.getName());
        orderRepository.refresh_orders();

        trainService.findByUser(user).forEach(t-> {
            if (orderRepository.findNotGenerationOrders(t.getId()).size() > 0){
                orderMaker.makeTrainOrders(t);
            }else if (findByTrainWaiting(t).size() < orderGenerator.getCarCountMax()){
                orderGenerator.generateOrders(t);
            }
        });

    }

    @Override
    @Transactional
    public void refreshTrainOrders(Train train) {
        log.debug("call method: refreshTrainOrders with train: " + train.getName());
        orderRepository.refresh_orders();
        if (orderRepository.findNotGenerationOrders(train.getId()).size() > 0){
            orderMaker.makeTrainOrders(train);
        }else if (findByTrainWaiting(train).size() < orderGenerator.getCarCountMax()){
            orderGenerator.generateOrders(train);
        }
    }

    private List<Order> findByTrainAndState(Train train, OrderState state) {
        List<Order> orders = orderRepository.findByTrainAndState(train, state);
        log.debug("call method findByTrainAndState with train: " + train.getName() + " state: " + state.getName() +
                ". Received order count: " + orders.size());
        return orders;
    }

    @Override
    public List<Order> findByTrainAndState(Train train, String orderStateName) {
        List<Order> orders = new ArrayList<>();

        try {
            OrderState state = orderStateService.findByName(orderStateName);
            orders = orderRepository.findByTrainAndState(train, state);
            log.debug("call method findByTrainAndState with train: " + train.getName() + " state: " + state.getName() +
                    ". Received order count: " + orders.size());
        } catch (OrderStateNotFoundException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> findByTrain(Train train) {
        List<Order> orders = orderRepository.findByTrain(train);
        log.debug("call method findByTrain with train: " + train.getName() +
                ". Received order count: " + orders.size());
        return orders;
    }

    @Override
    @Transactional
    public void startOrder(Order order) {
        orderRepository.start_order(order.getId(), order.getTrain().getId());
        log.debug("call method: startOrder with order: " + order.getName());
        entityManager.refresh(order);
    }

    @Override
    @Transactional
    public void finishOrder(Order order) {
        log.debug("call method: finishOrder with order: " + order.getName());
        if (order.getState().getName().equals(OrderStateName.DONE.get())){
            Train train = order.getTrain();
            train.setCorpsState(trainService.checkTechnicalStatus(train));
            trainService.save(train);

            Company company = train.getCompany();

            Float cash = company.getCash();
            company.setCash(cash + order.getProfit());
            companyService.save(company);

            delete(order);
            log.debug("delete order: " + order.getName() + " successfully");
        }
    }

    @Override
    public List<Order> findByTrainAndStateIn(Train train, List<OrderState> states) {
        List<Order> orders = orderRepository.findByTrainAndStateIn(train, states);
        log.debug("call method findByTrainAndStateIn with train: " + train.getName() +
                "and orderStates count: " + states.size() +
                ". Received order count: " + orders.size());
        return orders;
    }

    @Override
    public List<Order> findByNotGenerationOrders(Train train) {
        List<Order> orders = orderRepository.findNotGenerationOrders(train.getId());
        log.debug("call method findByNotGenerationOrders with train: " + train.getName() +
                ". Received order count: " + orders.size());
        return orders;
    }

    @Override
    public boolean checkUniqueName(String name) {
        log.debug("call method checkUniqueName with orderName: " + name +
                ". Received const value false");
        return false;
    }
}
