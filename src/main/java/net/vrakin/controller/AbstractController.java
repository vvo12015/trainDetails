package net.vrakin.controller;

import net.vrakin.model.*;
import net.vrakin.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class AbstractController {

    protected GeneralService generalService;

    protected String objectName;

    protected String pageName = "admin_table";

    protected Map<String, Object> model = new HashMap<>();

    protected List<String> errors = new ArrayList<>();

    protected Map<String, Object> listMap = new HashMap<>();

    @PostConstruct
    protected abstract void init();

    protected String capitalizeName(){

        String[] names;

        if (objectName.contains("_")) {
            names = objectName.split("_");
        }else {
            names = new String[1];
            names[0] = objectName;
        }

        String result = names[0].substring(0, 1).toUpperCase() + names[0].substring(1);

        for (int i = 1; i < names.length; i++) {
            result += " " + names[i];
        }

        return result.trim();
    }

    protected void setModelList(User user) {
        List<ShowContentsInList> prepareList = generalService.findAll();

        List<Map<String, String>> listValue = prepareList.stream().map(ShowContentsInList::toMap).collect(Collectors.toList());

        model.put("listValue", listValue);
        model.put("header_page", capitalizeName());
        model.put("path_page", objectName);
        model.put("user", user);
        model.put("errors", errors);
        createListMap();
    }

    protected ModelAndView getModelAndView(){
        return new ModelAndView(pageName, model);
    }

    protected void createListMap() {

        model.put("listMap", listMap);
    }
}
