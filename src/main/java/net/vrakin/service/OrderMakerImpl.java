package net.vrakin.service;

import net.vrakin.model.*;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderMakerImpl extends OrderGeneratorImpl implements OrderMaker {

    private static final int CAR_MASS = 70;
    public static final int PERCENT100 = 100;
    public static final int COEFICIENT_TIME = 60;
    public static final float SPEED_COEFICIENT_MAX = 0.1F;
    public static final float SPEED_COEFICIENT_MIN = -0.2F;

    @Override
    public void makeUserOrders(User user) {
        List<Train> trains = trainService.findByUser(user);

        trains.forEach(this::makeTrainOrders);
    }

    @Override
    public void makeTrainOrders(Train train){
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
        state = orderStateService.findByName(OrderStateName.DONE.get()).get(0);
        String trainCity = train.getCity().getName();
        Route route = order.getRoute();

        return route.getCity1().getName().equals(trainCity)?route.getCity2():route.getCity1();
    }

    private Integer getProfit(OrderState state) {
        if (state.getName().equals(OrderStateName.DEADLINE1.get())) {
            return Float.valueOf(profit * 1.2f).intValue();
        } else if (state.getName().equals(OrderStateName.BELATED.get())) {
            return Float.valueOf(profit * 0.8f).intValue();
        }
        return profit;
    }

    private int getExecution(int differenceTime) {
        return (differenceTime * getRealSpeed() * PERCENT100 / COEFICIENT_TIME) / getDistance();
    }

    private int getDifferenceTime() {
        return Long.valueOf(
                (Calendar.getInstance().getTimeInMillis() -
                        getCreationDate().getTimeInMillis())
                        / 1000).intValue();
    }

    private Integer getRealSpeed(){
        int power = train.getTrainMuseum().getPower();
        int difference = power - train.getTrainMuseum().getMass() - carCount * CAR_MASS;

        float speedCoeficient = 0;

        if (difference > 0.1*power) speedCoeficient = SPEED_COEFICIENT_MAX;

        if (difference < 0) speedCoeficient = SPEED_COEFICIENT_MIN;

        return Double.valueOf(train.getTrainMuseum().getSpeed() * (1 + speedCoeficient)).intValue();
    }
}
