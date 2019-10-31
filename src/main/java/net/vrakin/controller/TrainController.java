package net.vrakin.controller;

import net.vrakin.model.Company;
import net.vrakin.model.ShowContentsInList;
import net.vrakin.model.Train;
import net.vrakin.model.User;
import net.vrakin.service.CompanyService;
import net.vrakin.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Controller
public class TrainController extends AbstractController {

    protected final String name = "trains";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    @PostConstruct
    protected void init(){
        generalService = trainService;
        model.put("fields", Train.getFields());
        objectName = name;
    }

    @GetMapping("/my_trains")
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){
        setModelList(user);
        return getModelAndView();
    }
}
