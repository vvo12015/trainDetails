package net.vrakin.controller;

import net.vrakin.model.ShowContentsInList;
import net.vrakin.model.User;
import net.vrakin.service.GeneralService;
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
        String result = "";

        for (int i = 0; i < names.length; i++) {
            result += objectName.substring(0, 1).toUpperCase() + objectName.substring(1) + " ";
        }

        return result.trim();
    }

    protected void setModelList(User user) {
        List<ShowContentsInList> prepareList = generalService.findAll();

        List<Map<String, String>> valueList = prepareList.stream().map(ShowContentsInList::toMap).collect(Collectors.toList());
        List<String> fields = new ArrayList<>();
        if (prepareList.size() > 0)
            fields = prepareList.get(0).getFields();

        model.put("listValue", valueList);
        model.put("fields", fields);
        model.put("header_page", capitalizeName());
        model.put("path_page", objectName);
        model.put("user", user);
    }

    protected ModelAndView getModelAndView(){
        return new ModelAndView(pageName, model);
    }

}
