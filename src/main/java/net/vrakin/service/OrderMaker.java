package net.vrakin.service;

import net.vrakin.model.Train;
import net.vrakin.model.User;

public interface OrderMaker {

    void makeUserOrders(User user);
    void makeTrainOrders(Train train);
}
