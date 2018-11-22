package net.vrakin.controller;

import net.vrakin.model.*;
import net.vrakin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderController extends AbstractController{

    private final String name = "order";

    private User user;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private CargoSevice cargoSevice;

    @Autowired
    private RouteService routService;

    @Autowired
    private OrderStateService orderStateService;

    @Autowired
    private OrderGenerator orderGenerator;

    @Autowired
    private OrderMaker orderMaker;

    @GetMapping("/" + name)
    public ModelAndView getOrders(@AuthenticationPrincipal User user){

        orderService.refreshUserOrders(user);
        initPage(user);

        return getModelAndView();
    }

    @GetMapping("/" + name + "/train{id}")
    public ModelAndView orderOfTrain(@AuthenticationPrincipal User user,
            @PathVariable("id") Long train_id){
        Train train = trainService.findById(train_id);

        orderService.refreshTrainOrders(train);
        initPage(user);
        createListMap(train_id);
//        filter listValue for train
        model.put("listValue", orderService.findByTrain(train).stream().map(Order::toMap).collect(Collectors.toList()));
        return getModelAndView();
    }

    @GetMapping("/" + name + "_start/{id}")
    public ModelAndView startOrder(@AuthenticationPrincipal User user,
                                     @PathVariable("id") Long order_id){
        Order order = orderService.findById(order_id);
        orderService.startOrder(order);

        return orderOfTrain(user, order.getTrain().getId());
    }

    @GetMapping("/" + name + "_progress/{id}")
    public ModelAndView progresOrder(@AuthenticationPrincipal User user,
                                   @PathVariable("id") Long order_id){
        Order order = orderService.findById(order_id);

        return orderOfTrain(user, order.getTrain().getId());
    }

    @GetMapping("/" + name + "_finish/{id}")
    public ModelAndView finishOrder(@AuthenticationPrincipal User user,
                                   @PathVariable("id") Long order_id){
        Order order = orderService.findById(order_id);
        Long train_id = order.getTrain().getId();
        orderService.finishOrder(order);

        return orderOfTrain(user, train_id);
    }

    @Override
    protected void init() {
        generalService = orderService;
        objectName = name;
    }

    private void initPage(User user) {
        setModelList(user);
        model.put("company", companyService.findByUser(user).get(0));
    }


    private void createListMap(Long train_id) {
        Map<String, Object> listMap = new HashMap<>();

        listMap.put("refreshTrain", train_id.toString());
        listMap.put("orderState", orderStateService.findAll().stream().map(OrderState::toMap).collect(Collectors.toList()));
        listMap.put("cargo", cargoSevice.findAll().stream().map(Cargo::toMap).collect(Collectors.toList()));
        listMap.put("route", routService.findAll().stream().map(Route::toMap).collect(Collectors.toList()));

        model.put("listMap", listMap);
    }
}
