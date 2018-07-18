package net.vrakin.controller;

import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.service.TrainMuseumService;
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
public class TrainMuseumController {

    @Autowired
    private TrainMuseumService trainMuseumService;

    @GetMapping("/train_museum")
    public ModelAndView listTrain_museum(@AuthenticationPrincipal User user){
        Map<String, Object> model = getModelListTrainMuseum();

        setPageParametrs(user, model);

        return new ModelAndView("train_museum", model);
    }

    private void setPageParametrs(@AuthenticationPrincipal User user, Map<String, Object> model) {
        model.put("user", user.getUsername());
        model.put("header_page", "Train Museum");
        model.put("path_page", "train_museum");
    }

    private Map<String, Object> getModelListTrainMuseum() {
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = trainMuseumService.findAll();

        model.put("trainMuseum", trainMuseum);
        return model;
    }

    @PostMapping("/train_museum")
    public ModelAndView saveTrain_museum(@AuthenticationPrincipal User user,
                                         TrainMuseum trainMuseum){

        trainMuseumService.save(trainMuseum);

        Map<String, Object> model = getModelListTrainMuseum();
        setPageParametrs(user, model);

        return new ModelAndView("train_museum", model);
    }
}
