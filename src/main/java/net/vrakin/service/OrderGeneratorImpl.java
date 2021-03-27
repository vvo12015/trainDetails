package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.OrderStateNotFoundException;
import net.vrakin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@Slf4j
public class OrderGeneratorImpl extends Order implements OrderGenerator {

    public static final int CAR_COUNT_MAX = 10;
    public static final int FULL_WEAR = 10;
    public static final int ORDER_COUNT_MAX = 10;
    public static final int CARGO_QUANTITY_ZERO = 0;
    private Integer cargoQuantity;

    private Integer distance;

    private Integer speed;

    @Autowired
    protected CargoService cargoService;

    @Autowired
    protected RouteService routeService;

    @Autowired
    protected TrainService trainService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderStateService orderStateService;

    public Integer getCargoQuantity() {
        return cargoQuantity;
    }

    public void setCargoQuantity(Integer cargoQuantity) {
        this.cargoQuantity = cargoQuantity;
    }

    @Override
    public void generateOrders(Train train){
        log.debug("call method: generateOrders with train: " + train.getName());
        runOrders(train);
    }

    protected void runOrders(Train train){
        log.debug("call method with train: " + train);
        List<Order> orders = orderService.findByTrain(train);
        for (int i = 0; i < ORDER_COUNT_MAX - orders.size(); i++) {
            this.train = train;
            this.cargoQuantity = generateCargoQuantity();
            this.cargo = generateCargo();
            this.carCount = generateCarCount();
            this.route = generateRoute();
            this.distance = getDistance();
            this.speed = generateSpeed();
            this.deadline1 = (long) ((distance * 60) / speed);
            this.deadline2 = Double.valueOf(deadline1 * 1.2).longValue();
            this.waitingDeadline = deadline1;
            this.fullWear = generateFullWear();
            this.profit = generateProfit();
            this.execution = 0;
            try {
                this.state = orderStateService.findByName(OrderStateName.WAITING.get());
            } catch (OrderStateNotFoundException e) {
                e.printStackTrace();
            }

            Order order = new Order();
            orderService.save(getOrder(order));
            log.debug("save order: " + order.getId() + " successfully");
        }
    }

    private Integer generateProfit() {

        Integer difference = cargo.getMaxPrice().intValue() - cargo.getMinPrice().intValue();

        int distanceProfit = cargo.getMinPrice().intValue() + getRandomInteger(difference);

        int result = distanceProfit * distance;

        log.debug("call method: generateProfit with profit: " + result);

        return result;
    }

    private Integer generateFullWear() {
        log.debug("call method: generateFullWear with const FULL_WEAR: " + FULL_WEAR);
        return FULL_WEAR;
    }

    private Integer generateSpeed() {

        int speed = this.train.getTrainMuseum().getSpeed();
        log.debug("call method: generateSpeed. Generated speed: " + speed);

        return speed;
    }

    private Route generateRoute() {

        List<Route> routes = routeService.findByCity(this.train.getCity());

        int index = getRandomInteger(routes.size());

        Route route = routes.get(index);
        log.debug("call method: generateRoute. Generated route: " + route.getName());

        return route;
    }

    private Integer generateCarCount() {
        int cargoCount = getRandomInteger(CAR_COUNT_MAX);
        log.debug("call method: generateCarCount. Generated cargoCount: " + cargoCount);
        return cargoCount;
    }

    private Cargo generateCargo() {

        List<Cargo> cargoList = cargoService.findAll();

        int cargoIndex = getRandomInteger(cargoList.size());

        Cargo cargo = cargoList.get(cargoIndex);

        log.debug("call method: generateCargo. Generated cargo: " + cargo.getName());

        return cargo;
    }

    public static int getRandomInteger(Integer count) {
        int result =  Long.valueOf(Math.round(Math.random() * (count - 1))).intValue();
        log.debug("call method: getRandomInteger. Generated result: " + result);
        return result;
    }

    private Integer generateCargoQuantity() {
        log.debug("call method: generateCargoQuantity. Generated cargoQuantity" + CARGO_QUANTITY_ZERO);
        return CARGO_QUANTITY_ZERO;
    }

    @Override
    public Integer getCarCountMax() {
        log.debug("call method: getCarCountMax, cargo count max" + CAR_COUNT_MAX);
        return CAR_COUNT_MAX;
    }
}
