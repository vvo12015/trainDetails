package net.vrakin.controller;

import net.vrakin.model.City;
import net.vrakin.model.Route;
import net.vrakin.model.User;
import net.vrakin.service.CityService;
import net.vrakin.service.GeneralService;
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
public class RouteController extends AbstractController {

    protected final String name = "route";

    @Autowired
    private GeneralService<Route> routeService;

    @Autowired
    private CityService cityService;

    @PostConstruct
    protected void init(){
        generalService = routeService;
        model.put("fields", Route.getFields());
        objectName = name;
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveRoute(@AuthenticationPrincipal User user,
                                         Route route){

        routeService.save(route);
        setModelList(user);
        return getModelAndView();
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        Route route = routeService.findById(id);
        routeService.delete(route);
        setModelList(user);

        return getModelAndView();
    }

    @Override
    protected void setModelList(User user) {
        super.setModelList(user);

        createListMap();
    }

    protected void createListMap() {
        List<Map<String, String>> cities = cityService.findAll().stream().map(City::toMap).collect(Collectors.toList());

        listMap.put("city1", cities);
        listMap.put("city2", cities);

        super.createListMap();
    }
}
