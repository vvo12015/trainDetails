package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TrainController extends AbstractTrainController {

    @GetMapping("/my_trains")
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){

        Company company = companyService.findByUser(user).get(0);
        Map<String, Object> model = getModelList(company);

        setPageParameters(user, "My trains", "trains", model);

        return new ModelAndView("trains", model);
    }

    Map<String, Object> getModelList(Company company) {
        Map<String, Object> model = new HashMap<>();
        model.put("trains", trainService.findByCompany(company));

        return model;
    }
}
