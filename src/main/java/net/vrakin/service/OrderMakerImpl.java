package net.vrakin.service;

import net.vrakin.model.*;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderMakerImpl extends OrderGeneratorImpl implements OrderMaker {

    private static final int CAR_MASS = 70;

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

            execution = (differenceTime * getRealSpeed() * 5 / 3) / getDistance();
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
        int difference = train.getTrainMuseum().getPower() - train.getTrainMuseum().getMass() - carCount * CAR_MASS;

        float speedCoeficient = 0;

        if (difference >= 0) speedCoeficient = 0.1F;

        if (difference < 0) speedCoeficient = -0.8F;

        return Double.valueOf(train.getTrainMuseum().getSpeed() * (1 + speedCoeficient)).intValue();
    }
}
