package net.vrakin.controller;

import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.service.TrainMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
public class TrainMuseumController extends AbstractController {

    protected final String name = "train_museum";

    @Autowired
    private TrainMuseumService trainMuseumService;

    @PostConstruct
    protected void init(){
        generalService = trainMuseumService;
        objectName = name;
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveTrainMuseum(@AuthenticationPrincipal User user,
                                  TrainMuseum trainMuseum){

        trainMuseumService.save(trainMuseum);
        setModelList(user);
        return getModelAndView();
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        TrainMuseum trainMuseum = trainMuseumService.findById(id);
        trainMuseumService.delete(trainMuseum);
        setModelList(user);

        return getModelAndView();
    }
}
