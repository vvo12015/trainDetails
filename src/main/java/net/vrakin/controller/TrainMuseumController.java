package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TrainMuseumController extends AbstractController {

    protected final String name = "train_museum";

    @Autowired
    private TrainMuseumService trainMuseumService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");

        generalService = trainMuseumService;
        model.put("fields", TrainMuseum.getFields());
        objectName = name;
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){
        log.debug("call method: toList with user: " + user.getUsername());

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveTrainMuseum(@AuthenticationPrincipal User user,
                                  TrainMuseum trainMuseum){
        log.debug("call method: saveTrainMuseum with user: " + user.getUsername() + "with trainMuseum object");

        if (generalService.checkUniqueName(trainMuseum.getName())){
            errors.add("Тhe name is not unique");
            log.debug("Тhe name is not unique");
        }else {
            trainMuseumService.save(trainMuseum);
            log.debug("save trainMuseum with name " + trainMuseum.getName() + " successfully");

        }
        setModelList(user);
        return getModelAndView();
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        log.debug("call method: delete with user: " +  user.getUsername() + " and id: " + id);

        TrainMuseum trainMuseum = trainMuseumService.findById(id);
        trainMuseumService.delete(trainMuseum);
        log.debug("delete trainMuseum with name " + trainMuseum.getName() + " successfully");

        setModelList(user);

        return getModelAndView();
    }

}
