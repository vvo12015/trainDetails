package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private TrainService trainService;

    @GetMapping("/company_page")
    public ModelAndView companyPage(@AuthenticationPrincipal User user){
        log.debug("call method: company_page");
        Map<String, Object> model = new HashMap<>();
        if (companyService.findByUser(user).size() == 0){
            log.debug("don't found company");
            companyService.registrationCompany(user);
            log.debug("create company successfully");
        };
        Company company = companyService.findByUser(user).get(0);
        model.put("company", company);
        model.put("user", user);
        log.debug("company: " + company.getName());
        log.debug("user: " + user.getUsername());
        return new ModelAndView("main", model);
    }
}
