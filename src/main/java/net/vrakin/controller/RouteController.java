package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RouteController extends AbstractController {

    protected final String name = "route";

    @Autowired
    private GeneralService<Route> routeService;

    @Autowired
    private CityService cityService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        generalService = routeService;
        model.put("fields", Route.getFields());
        objectName = name;
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){
        log.debug("call method: toList with user: " + user.getUsername());
        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveRoute(@AuthenticationPrincipal User user,
                                         Route route){
        log.debug("call method: saveRoute with user: " + user.getUsername() + " and route object");

        routeService.save(route);
        log.debug("saving route: " + route.toString() + " successfully");

        setModelList(user);
        return getModelAndView();
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        log.debug("call delete: saveRoute with user: " + user.getUsername() + " and route id " + id);

        Route route = routeService.findById(id);
        routeService.delete(route);
        log.debug("deleting route with id: " + id + " successfully");
        setModelList(user);

        return getModelAndView();
    }

    @Override
    protected void setModelList(User user) {
        super.setModelList(user);
        log.debug("call method: setModelList with user: " + user.getUsername());

        createListMap();
    }

    protected void createListMap() {
        log.debug("call method: createListMap");

        List<Map<String, Object>> cities = cityService.findAll().stream().map(City::toMap).collect(Collectors.toList());

        listMap.put("city1", cities);
        listMap.put("city2", cities);

        super.createListMap();
    }
}
