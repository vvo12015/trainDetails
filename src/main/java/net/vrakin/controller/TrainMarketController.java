package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class TrainMarketController extends AbstractTrainController {

    @GetMapping("/train_market")
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){
        Map<String, Object> model = getModelList();

        setPageParameters(user, "Train Market", "train_market", model);

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
        Map<String, Object> model = getModelList();
        setPageParameters(user, "Train Market", "train_market", model);
        return new ModelAndView("train_market", model);
    }
}
