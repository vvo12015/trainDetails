package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.*;
import net.vrakin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class OrderAdminController extends AbstractController {

    protected final String name = "order";

    @Autowired
    private GeneralService<Order> orderService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private OrderStateService orderStateService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        objectName = name;
        generalService = orderService;
        model.put("fields", Order.getFields());
    }

    @PostMapping("/" + name)
    public ModelAndView saveOrder(@AuthenticationPrincipal User user,
                                         Order order){
        log.debug("call method: saveOrder with user: " + user.getUsername() + " and order object");

        orderService.save(order);
        log.debug("saving order: " + order.getId() + " successfully");
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){

        log.debug("call method: delete with user: " + user.getUsername() + " and order id");
        Order order = orderService.findById(id);
        orderService.delete(order);
        log.debug("deleting order with " + id + " successfully");
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }

    @Override
    protected void setModelList(User user) {
        super.setModelList(user);
        log.debug("call method: setModelList with user " + user.getUsername());

        List<Map<String, Object>> routes = routeService.findAll().stream().map(Route::toMap).collect(Collectors.toList());
        List<Map<String, Object>> cargo = cargoService.findAll().stream().map(Cargo::toMap).collect(Collectors.toList());
        List<Map<String, Object>> trains = trainService.findAll().stream().map(Train::toMap).collect(Collectors.toList());
        List<Map<String, Object>> states = orderStateService.findAll().stream().map(OrderState::toMap).collect(Collectors.toList());

        Map<String, Object> listMap = new HashMap<>();

        listMap.put("route", routes);
        listMap.put("cargo", cargo);
        listMap.put("train", trains);
        listMap.put("state", states);

        model.put("listMap", listMap);
    }
}
