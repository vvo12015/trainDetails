package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.service.CompanyService;
import net.vrakin.service.TrainMuseumService;
import net.vrakin.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTrainController {

    @Autowired
    CompanyService companyService;

    @Autowired
    TrainService trainService;

    @Autowired
    TrainMuseumService trainMuseumService;

    void setPageParameters(User user,
                           String headerPage, String pathPage,
                           Map<String, Object> model) {
        Company company = companyService.findByUser(user).get(0);
        company.setTrains(trainService.findByCompany(company));
        model.put("company", company);
        model.put("header_page", headerPage);
        model.put("path_page", pathPage);
    }

    Map<String, Object> getModelList() {
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = trainMuseumService.findAll();

        model.put("trainMuseum", trainMuseum);
        return model;
    }

}
