package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.model.*;
import net.vrakin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class DetailsController extends AbstractController {

    protected final String name = "detail";

    @Autowired
    private DetailMuseumService detailMuseumService;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private CompanyService companyService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        objectName = name;
        generalService = detailService;
        model.put("fields", Detail.getFields());
        model.put("save_not", true);
    }

    @GetMapping("/" + name + "/train{id}")
    public ModelAndView toList(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long train_id){
        log.debug("call method toList with user: " + user.getUsername() + " and train_id " + train_id);
        setModelList(user);
        model.put("listValue", detailService.findAllWithButton(train_id));
        try {
            model.put("company", companyService.findByUser(user).get(0));
        } catch (CompanyNotFoundException e) {
            companyService.registrationCompany(user);
        }
        model.put("detailMuseumList", detailMuseumService.findOfTrainWithButton(train_id));
        model.put("detailMuseumFields", DetailMuseum.getFields());
        this.pageName = "details";
        return getModelAndView();
    }
}
