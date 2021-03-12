package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.*;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class OrderMakerImpl extends OrderGeneratorImpl implements OrderMaker {

    private static final int CAR_MASS = 70;
    public static final int PERCENT100 = 100;
    public static final int COEFFICIENT_TIME = 60;
    public static final float SPEED_COEFFICIENT_MAX = 0.1F;
    public static final float SPEED_COEFFICIENT_MIN = -0.2F;

    @Override
    public void makeUserOrders(User user) {

        log.debug("call method: makeUserOrders with user: " + user.getUsername());

        List<Train> trains = trainService.findByUser(user);

        trains.forEach(this::makeTrainOrders);
    }

    @Override
    public void makeTrainOrders(Train train){
        log.debug("call method: makeTrainOrders with train" + train.getName());
        Order order = orderService.findByTrainActionOrder(train);
        if (order != null) {
            setOrder(order);
            int differenceTime = getDifferenceTime();

            execution = getExecution(differenceTime);
            if (execution > 100) {
                execution = 100;
                profit = getProfit(state);
                train.setCity(getCurrentCity(train, order));
                trainService.save(train);
            }

            orderService.save(getOrder(order));
        }
    }

    private City getCurrentCity(Train train, Order order) {

        state = orderStateService.findByName(OrderStateName.DONE.get());
        String trainCity = train.getCity().getName();
        Route route = order.getRoute();

        City city = route.getCity1().getName().equals(trainCity)?route.getCity2():route.getCity1();

        log.debug("call method: getCurrentCity with train: " + train.getName() + " and order: " + order.getName() +
                " with state: " + state.getName() + " with route: " + route.getName() + " . Received city: " + city.getName());
        return city;
    }

    private Integer getProfit(OrderState state) {

        if (state.getName().equals(OrderStateName.DEADLINE1.get())) {
            log.debug("call method getProfit with orderstate: " + state.getName() + ". Received profit: " + profit);
            return Float.valueOf(profit * 1.2f).intValue();
        } else if (state.getName().equals(OrderStateName.BELATED.get())) {
            log.debug("call method getProfit with orderstate: " + state.getName() + ". Received profit: " + profit);
            return Float.valueOf(profit * 0.8f).intValue();
        }
        log.debug("call method getProfit with orderstate: " + state.getName() + ". Received profit: " + profit);
        return profit;
    }

    private int getExecution(int differenceTime) {
        int result = (differenceTime * getRealSpeed() * PERCENT100 / COEFFICIENT_TIME) / getDistance();
        log.debug("call method: getExecution, with differenceTime " + differenceTime + ". Received execution" + result);
        return result;
    }

    private int getDifferenceTime() {
        int result =  Long.valueOf(
                (Calendar.getInstance().getTimeInMillis() -
                        getCreationDate().getTimeInMillis())
                        / 1000).intValue();
        log.debug("call method: getDifferenceTime. Received differenceTime" + result);
        return result;
    }

    private Integer getRealSpeed(){
        int power = train.getTrainMuseum().getPower();
        int difference = power - train.getTrainMuseum().getMass() - carCount * CAR_MASS;

        float speedCoeficient = 0;

        if (difference > 0.1*power) speedCoeficient = SPEED_COEFFICIENT_MAX;

        if (difference < 0) speedCoeficient = SPEED_COEFFICIENT_MIN;

        int result  = Double.valueOf(train.getTrainMuseum().getSpeed() * (1 + speedCoeficient)).intValue();

        log.debug("call method: getRealSpeed. Received realSpeed" + result);

        return result;
    }
}
