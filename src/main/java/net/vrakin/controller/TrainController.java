package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.model.Company;
import net.vrakin.model.ShowContentsInList;
import net.vrakin.model.Train;
import net.vrakin.model.User;
import net.vrakin.service.CompanyService;
import net.vrakin.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@Slf4j
public class TrainController extends AbstractController {

    protected final String name = "trains";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TrainService trainService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        generalService = trainService;
        model.put("fields", Train.getFields());
        objectName = name;
    }

    @GetMapping("/my_trains")
    public ModelAndView listTrainMarket(@AuthenticationPrincipal User user){
        log.debug("call method: listTrainMarket with user: " + user.getUsername());
        setModelList(user);
        return getModelAndView();
    }

    @Override
    protected void setModelList(User user) {
        log.debug("call method: setModelList with user: " + user.getUsername());
        pageName = objectName;
        Company company = null;
        try {
            company = companyService.findByUser(user).get(0);
        } catch (CompanyNotFoundException e) {
            companyService.registrationCompany(user);
            e.printStackTrace();
        }
        company.setTrains(trainService.findByCompany(company));
        model.put("company", company);
        model.put("header_page", capitalizeName());
        model.put("path_page", objectName);
        model.put("user", user);

        model.put("trains", trainService.findByCompany(company).stream()
                .map(ShowContentsInList::toMap)
                .collect(Collectors.toList()));
    }
}
