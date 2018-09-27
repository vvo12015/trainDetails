package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.User;
import net.vrakin.service.CompanyService;
import net.vrakin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/orders")
    public ModelAndView getOrders(@AuthenticationPrincipal User user){

        Map<String, Object> model = new HashMap<>();
        model.put("orders", orderService.findByUser(user));
        model.put("wait_orders", orderService.findWaitOrderNow());

        Company company = companyService.findByUser(user).get(0);
        model.put("company", company);

        orderService.refresh();

        return new ModelAndView("orders", model);
    }
}
