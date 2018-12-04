package net.vrakin.controller;

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
import java.util.Map;

@Controller
public class TrainMarketController extends AbstractController {

    protected final String name = "train_market";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private CityService cityService;

    @GetMapping("/" + name)
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){

        setModelList(user);

        return getModelAndView();
    }

    @GetMapping("/" + name + "_buy/{id}")
    public ModelAndView trainMarketBuy(@AuthenticationPrincipal User user,
                                 @PathVariable("id") Long trainMuseum_id){

        trainService.trainBuy(user, trainMuseum_id);
        setModelList(user);
        return getModelAndView();
    }

    @GetMapping("/train_museum_buy/{id}")
    public ModelAndView trainMuseumBuy(@AuthenticationPrincipal User user,
                                 @PathVariable("id") Long trainMuseum_id){
        return trainMarketBuy(user, trainMuseum_id);
    }

    @Override
    protected void setModelList(User user) {
        super.setModelList(user);
        Company company = companyService.findByUser(user).get(0);
        company.setTrains(trainService.findByCompany(company));
        model.put("company", company);
    }


    @PostConstruct
    @Override
    public void init() {
        generalService = trainMuseumService;
        objectName = name;
        model.put("fields", TrainMuseum.getFields());
    }
}
