package net.vrakin.controller;

import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class TrainMuseumController extends AbstractTrainController {

    @GetMapping("/train_museum")
    public ModelAndView listTrain_museum(@AuthenticationPrincipal User user){
        Map<String, Object> model = getModelList();

        setPageParameters(user, "Train Museum", "train_museum", model);

        return new ModelAndView("train_museum", model);
    }

    @GetMapping("/train_museum/{id}")
    public ModelAndView showTrainMuseum(@AuthenticationPrincipal User user,
                                          @PathVariable Long id){
        Map<String, Object> model = getModelList();

        setPageParameters(user, "Train Museum", "train_museum", model);

        return new ModelAndView("train_museum", model);
    }

    @PostMapping("/train_museum")
    public ModelAndView saveTrainMuseum(@AuthenticationPrincipal User user,
                                         TrainMuseum trainMuseum){

        trainMuseumService.saveOrUpdate(trainMuseum);
        Map<String, Object> model = getModelList();
        setPageParameters(user, "Train Museum", "train_museum", model);
        return new ModelAndView("train_museum", model);
    }

    @GetMapping("/trainMuseum_remove/{id}")
    private ModelAndView deleteTrainMuseum(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        TrainMuseum trainMuseum = trainMuseumService.load(id);
        trainMuseumService.delete(trainMuseum);
        Map<String, Object> model = getModelList();
        setPageParameters(user, "Train Museum", "train_museum", model);

        return new ModelAndView("train_museum", model);
    }
}
