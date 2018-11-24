package net.vrakin.service;

import net.vrakin.model.OrderState;

import java.util.List;
import java.util.Optional;

public interface OrderStateService extends GeneralService<OrderState>{

    OrderState getWaitState();
    List<OrderState> findByNames(List<String> names);
    OrderState findByName(String name);
    List<OrderState> findByInMotion(boolean inMotion);

}
