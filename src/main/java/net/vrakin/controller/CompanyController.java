package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.service.CompanyService;
import net.vrakin.service.TrainMuseumService;
import net.vrakin.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private TrainService trainService;

    @GetMapping("/company_page")
    public ModelAndView companyPage(@AuthenticationPrincipal User user){
        Map<String, Object> model = new HashMap<>();
        if (companyService.findByUser(user).size() == 0){
            companyService.registrationCompany(user);
        }
        Company company = companyService.findByUser(user).get(0);
        model.put("company", company);
        return new ModelAndView("main", model);
    }
}
