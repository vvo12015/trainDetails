package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.service.CompanyService;
import net.vrakin.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/train_market")
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){

        setModelList(user);

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
        setModelList(user);
        return new ModelAndView("train_market", model);
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
        generalService = trainService;
        objectName = name;
    }
}
