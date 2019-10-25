package net.vrakin.controller;

import net.vrakin.model.City;
import net.vrakin.model.TrainStatus;
import net.vrakin.model.User;
import net.vrakin.service.GeneralService;
import net.vrakin.service.TrainStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
public class TrainStatusController extends AbstractController {

    protected final String name = "train_status";
    
    @Autowired
    protected GeneralService<TrainStatus> trainStatusService;

    @PostConstruct
    protected void init(){
        objectName = name;
        generalService = trainStatusService;
        model.put("fields", TrainStatus.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveCity(@AuthenticationPrincipal User user,
                                  TrainStatus trainStatus){

        if (generalService.checkUniqueName(trainStatus.getName())){
            errors.add("Тhe name is not unique");
        }else {
            generalService.save(trainStatus);
        }
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        TrainStatus trainStatus = trainStatusService.findById(id);
        trainStatusService.delete(trainStatus);
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }
}
