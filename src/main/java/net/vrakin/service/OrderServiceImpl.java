package net.vrakin.service;

import net.vrakin.model.*;
import net.vrakin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyService companiService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private OrderStateService orderStateService;

    @Override
    public void refresh() {
        List<Order> cancelledOrders =
                findWaitOrderNow();
        System.out.println(cancelledOrders.get(0));
    }

    @Override
    public List<Order> findByUser(User user) {
        Company company = companiService.findByUser(user).get(0);

        List<Train> trains = trainService.findByCompany(company);
        List<Order> orders = new ArrayList<>();

        for (Train train :
                trains) {
            orders.addAll(orderRepository.findByTrain(train));
        }
        return orders;
    }

    @Override
    public List<Order> findWaitOrderNow() {
        Date currentDate = orderRepository.currentDate();
        return orderRepository.findByWaitingDeadlineBefore(currentDate);
    }
}
