package net.vrakin.service;

import net.vrakin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class OrderGeneratorImpl extends Order implements OrderGenerator {

    public static final int CAR_COUNT_MAX = 10;
    public static final int FULL_WEAR = 10;
    public static final int ORDER_COUNT_MAX = 10;
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
        runOrders(train);
    }

    protected void runOrders(Train train){
        List<Order> orders = orderService.findByTrain(train);
        for (int i = 0; i < ORDER_COUNT_MAX - orders.size(); i++) {
            this.train = train;
            this.cargoQuantity = generateCargoQuantity();
            this.cargo = generateCargo();
            this.carCount = generateCarCount();
            this.route = generateRoute();
            this.distance = getDistance();
            this.speed = generateSpeed();
            this.deadline1 = Long.valueOf((distance * 60) / speed);
            this.deadline2 = Double.valueOf(deadline1 * 1.2).longValue();
            this.waitingDeadline = deadline1;
            this.fullWear = generateFullWear();
            this.profit = generateProfit();
            this.execution = 0;
            this.state = orderStateService.findByName(OrderStateName.WAITING.get());

            Order order = new Order();
            orderService.save(getOrder(order));
        }
    }

    private Integer generateProfit() {

        Integer difference = cargo.getMaxPrice().intValue() - cargo.getMinPrice().intValue();

        int distanceProfit = cargo.getMinPrice().intValue() + getRandomInteger(difference);

        return distanceProfit * distance;
    }

    private Integer generateFullWear() {
        return FULL_WEAR;
    }

    private Integer generateSpeed() {
        return this.train.getTrainMuseum().getSpeed();
    }

    private Route generateRoute() {

        List<Route> routes = routeService.findByCity(this.train.getCity());

        int index = getRandomInteger(routes.size());

        return routes.get(index);
    }

    private Integer generateCarCount() {
        return getRandomInteger(CAR_COUNT_MAX);
    }

    private Cargo generateCargo() {

        List<Cargo> cargoList = cargoService.findAll();

        int cargoIndex = getRandomInteger(cargoList.size());

        return cargoList.get(cargoIndex);
    }

    public static int getRandomInteger(Integer count) {
        return Long.valueOf(Math.round(Math.random() * (count - 1))).intValue();
    }

    private Integer generateCargoQuantity() {
        return 0;
    }

    @Override
    public Integer getCarCountMax() {
        return CAR_COUNT_MAX;
    }
}
