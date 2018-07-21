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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrainMarketController{

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    private void setPageParametrs(User user,
                                  Map<String, Object> model) {
        Company company = companyService.findByUser(user).get(0);
        model.put("user", user.getUsername());
        model.put("company", company);
        model.put("header_page", "Train Market");
        model.put("path_page", "train_market");
    }

    @GetMapping("/train_market")
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){
        Map<String, Object> model = getModelListTrainMuseum();

        setPageParametrs(user, model);

        return new ModelAndView("train_market", model);
    }

    @PostMapping("/train_market")
    public ModelAndView trainBuy(@AuthenticationPrincipal User user,
                                 TrainMuseum trainMuseum){

        Company company = companyService.findByUser(user).get(0);
        Long trainOfTypeCount = company.getTrains()
                .stream()
                .filter(train -> train.getTrainMuseum().equals(trainMuseum))
                .count();
        Train train = new Train(
                trainMuseum.getName() + trainOfTypeCount.toString(),
                company,
                trainMuseum
                );
        company.setCash(company.getCash() - trainMuseum.getPrice());
        companyService.save(company);
        trainService.save(train);
        Map<String, Object> model = getModelListTrainMuseum();
        setPageParametrs(user, model);
        return new ModelAndView("train_market", model);
    }

    private Map<String, Object> getModelListTrainMuseum() {
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = trainMuseumService.findAll();

        model.put("trainMuseum", trainMuseum);
        return model;
    }
}
