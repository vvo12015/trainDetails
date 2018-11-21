package net.vrakin.service;

import net.vrakin.model.Train;

public interface OrderGenerator {

    void generateOrders(Train train);
    Integer getCarCountMax();
}
