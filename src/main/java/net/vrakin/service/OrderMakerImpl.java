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
    public void makeOrders(User user) {
        List<Train> trains = trainService.findByUser(user);

        trains.forEach(this::runOrders);
    }

    protected void runOrders(Train train){
        Order order = orderService.findByTrainActionOrder(train);
        if (order != null) {
            setOrder(order);
            int differenceTime =
                    Long.valueOf(
                            (Calendar.getInstance().getTimeInMillis() -
                                    getCreationDate().getTimeInMillis())
                                    / 1000).intValue();

            execution = (differenceTime * getRealSpeed() * PERCENT100 / COEFICIENT_TIME) / getDistance();
            if (execution > 100) {
                execution = 100;
                if (state.getName().equals(OrderStateName.DEADLINE1)) {
                    profit = Float.valueOf(profit * 1.2f).intValue();
                } else if (state.getName().equals(OrderStateName.BELATED)) {
                    profit = Float.valueOf(profit * 0.8f).intValue();
                }
                state = orderStateService.findByName(OrderStateName.DONE.get()).get(0);
                String trainCity = train.getCity().getName();
                Route route = order.getRoute();

                City currentCity = route.getCity1().getName().equals(trainCity)?route.getCity2():route.getCity1();
                train.setCity(currentCity);
                trainService.save(train);
            }

            orderService.save(getOrder(order));
        }
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
