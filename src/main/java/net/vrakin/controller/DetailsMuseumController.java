package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.DetailMuseum;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.service.DetailMuseumService;
import net.vrakin.service.GeneralService;
import net.vrakin.service.TrainMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class DetailsMuseumController extends AbstractController {

    protected final String name = "detail_museum";

    @Autowired
    private DetailMuseumService detailMuseumService;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        objectName = name;
        generalService = detailMuseumService;
        model.put("fields", DetailMuseum.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){
        log.debug("call method: toList");
        setModelList(user);
        createListMap();

        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveDetailMuseum(@AuthenticationPrincipal User user,
                                  DetailMuseum detailMuseum){
        log.debug("call method: saveDetailMuseum with user: " + user.getUsername() + " and detailMuseum object");
        if (generalService.checkUniqueName(detailMuseum.getName())){
            errors.add("Тhe name is not unique");
            log.debug("Тhe name is not unique");
        }else {
            generalService.save(detailMuseum);
        }
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        DetailMuseum detailMuseum = detailMuseumService.findById(id);
        detailMuseumService.delete(detailMuseum);
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_add_{trainMuseum_id}/{detail_id}")
    public String addDetailToTrainMuseum(@AuthenticationPrincipal User user,
                               @PathVariable("detail_id") Long detailId,
                               @PathVariable("trainMuseum_id") Long trainMuseumId){
        DetailMuseum detailMuseum = detailMuseumService.findById(detailId);
        TrainMuseum trainMuseum = trainMuseumService.findById(trainMuseumId);
        List<DetailMuseum> detailMuseumList = trainMuseum.getDetails();
        detailMuseumList.add(detailMuseum);
        trainMuseum.setDetails(detailMuseumList);
        trainMuseumService.save(trainMuseum);

        return "redirect:" + "/train_museum_details/" + trainMuseumId;
    }

    @GetMapping("/" + name + "_remove_{trainMuseum_id}/{detail_id}")
    public String removeDetailToTrainMuseum(@AuthenticationPrincipal User user,
                                               @PathVariable("detail_id") Long detailId,
                                               @PathVariable("trainMuseum_id") Long trainMuseumId){
        DetailMuseum detailMuseum = detailMuseumService.findById(detailId);
        TrainMuseum trainMuseum = trainMuseumService.findById(trainMuseumId);
        List<DetailMuseum> detailMuseumList = trainMuseum.getDetails().stream()
                .filter(d-> detailMuseum.getId()!=d.getId())
                .collect(Collectors.toList());
        trainMuseum.setDetails(detailMuseumList);
        trainMuseumService.save(trainMuseum);

        return "redirect:" + "/train_museum_details/" + trainMuseumId;
    }
    @Override
    protected void createListMap() {

        Map<String, String> valueTrue = new HashMap<>();
        valueTrue.put("id", "1");
        valueTrue.put("name", "true");
        Map<String, String> valueFalse = new HashMap<>();
        valueFalse.put("id", "0");
        valueFalse.put("name", "false");
        List<Map<String, String>> listIsRepaired = new ArrayList<>();

        listIsRepaired.add(valueTrue);
        listIsRepaired.add(valueFalse);
        listMap.put("isRepaired", listIsRepaired);

        super.createListMap();
    }

    @GetMapping("/train_museum_details/{id}")
    public ModelAndView trainMuseumDetails(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long trainMuseum_id){
        initPage(trainMuseum_id);
        setModelList(user);
        TrainMuseum trainMuseum = trainMuseumService.findById(trainMuseum_id);
        model.put("listValue", detailMuseumService.findAllWithButton(trainMuseum));
        return getModelAndView();
    }

    @GetMapping("/train_market_details/{id}")
    public ModelAndView trainMarketDetails(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long trainMuseum_id){
        return trainMuseumDetails(user, trainMuseum_id);
    }

    private void initPage(Long trainMuseum_id){
        TrainMuseum trainMuseum = trainMuseumService.findById(trainMuseum_id);

        model.put("trainMuseum", trainMuseum.toMap());
        model.put("trainMuseumFields", TrainMuseum.getFields());

    }
}
