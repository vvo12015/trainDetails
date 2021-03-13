package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.exception.TrainNotFoundException;
import net.vrakin.model.*;
import net.vrakin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
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
    private CargoService cargoService;

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

        log.debug("call method: getOrders with user: " + user.getUsername());
        orderService.refreshUserOrders(user);
        initPage(user);

        return getModelAndView();
    }

    @GetMapping("/" + name + "/train{id}")
    public ModelAndView orderOfTrain(@AuthenticationPrincipal User user,
            @PathVariable("id") Long train_id){
        log.debug("call method: orderOfTrain with user: " + user.getUsername() + " and train_id " + train_id);
        Train train = trainService.findById(train_id);

        orderService.refreshTrainOrders(train);
        initPage(user);

        model.put("listValue", orderService.findByTrain(train).stream().map(Order::toMap).collect(Collectors.toList()));
        return getModelAndView();
    }

    @GetMapping("/" + name + "_start/{id}")
    public ModelAndView startOrder(@AuthenticationPrincipal User user,
                                     @PathVariable("id") Long order_id){
        log.debug("call method: startOrder with user: " + user.getUsername() + " and order_id: " + order_id );
        Order order = orderService.findById(order_id);
        orderService.startOrder(order);

        return getOrders(user);
    }

    @GetMapping("/" + name + "_progress/{id}")
    public ModelAndView progressOrder(@AuthenticationPrincipal User user,
                                      @PathVariable("id") Long order_id){
        log.debug("call method progressOrder with user: " + user.getUsername() + " and order_id: " + order_id);
        Order order = orderService.findById(order_id);

        return getOrders(user);
    }

    @GetMapping("/" + name + "_finish/{id}")
    public ModelAndView finishOrder(@AuthenticationPrincipal User user,
                                   @PathVariable("id") Long order_id){
        log.debug("call method finishOrder with user: " + user.getUsername() + " and order_id: " + order_id);

        Order order = orderService.findById(order_id);
        Long train_id = order.getTrain().getId();
        orderService.finishOrder(order);

        return getOrders(user);
    }

    @Override
    protected void init() {
        log.debug("call method: init");
        generalService = orderService;
        model.put("fields", Order.getFields());
        objectName = name;
    }

    private void initPage(User user) {
        log.debug("call method: initPage");
        setModelList(user);
        List<Map<String, Object>> orders = null;
        try {
            orders = orderService.findByUser(user)
                    .stream()
                    .map(Order::toMap)
                    .collect(Collectors.toList());
        } catch (CompanyNotFoundException e) {
            companyService.registrationCompany(user);
        }
        Company company = null;
        try {
            company = companyService.findByUser(user).get(0);
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        model.put("listValue", orders);
        model.put("company", company);
        String trainName = "поїздів не знайдено";
        if (!orders.isEmpty()) {
            Train train = null;
            try {
                train = trainService.findByCompanyAndName(company, orders.get(0).get("train").toString());
            } catch (TrainNotFoundException e) {

                e.printStackTrace();
            }
            trainName = train.getId().toString();
        }
        model.put("refreshTrain", trainName);
    }

    @Override
    protected void createListMap() {
        log.debug("call method: createListMap");
        Map<String, Object> listMap = new HashMap<>();

        List<Map<String, String>> listValue;
        try {
            listValue = (List<Map<String, String>>) model.get("listValue");
            if (listValue.size() > 0) {
                listMap.put("orderState", orderStateService.findAll().stream().map(OrderState::toMap).collect(Collectors.toList()));
                listMap.put("cargo", cargoService.findAll().stream().map(Cargo::toMap).collect(Collectors.toList()));
                listMap.put("route", routService.findAll().stream().map(Route::toMap).collect(Collectors.toList()));
            }
        }catch (ClassCastException ex){
            ex.printStackTrace();
        }finally {
            model.put("listMap", listMap);
        }
    }
}
