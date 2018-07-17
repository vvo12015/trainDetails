package net.vrakin.controller;

import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.TrainMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private TrainMuseumRepository trainMuseumRepository;

    @RequestMapping("/")
    public ModelAndView index(){
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = (List<TrainMuseum>) trainMuseumRepository.findAll();

        model.put("name", "Valentin");
        model.put("trains", trainMuseum);
        return new ModelAndView("index", model);
    }

}
