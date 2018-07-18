package net.vrakin.controller;

import net.vrakin.model.Role;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.repository.TrainMuseumRepository;
import net.vrakin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrainMuseumController {

    @Autowired
    private TrainMuseumRepository trainMuseumRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/train_museum")
    public ModelAndView listTrain_museum(@AuthenticationPrincipal User user){
        Map<String, Object> model = getModelListTrainMuseum(user);
        return new ModelAndView("train_museum", model);
    }

    private Map<String, Object> getModelListTrainMuseum(@AuthenticationPrincipal User user) {
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = (List<TrainMuseum>) trainMuseumRepository.findAll();

        model.put("user", user.getUsername());
        model.put("trains", trainMuseum);
        return model;
    }

    @PostMapping("/train_museum")
    public ModelAndView saveTrain_museum(@AuthenticationPrincipal User user,
                                         TrainMuseum trainMuseum){

        trainMuseumRepository.save(trainMuseum);

        Map<String, Object> model = getModelListTrainMuseum(user);

        return new ModelAndView("train_museum", model);
    }
}
