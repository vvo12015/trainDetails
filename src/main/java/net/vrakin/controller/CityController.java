package net.vrakin.controller;

import net.vrakin.model.City;
import net.vrakin.model.User;
import net.vrakin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
public class CityController extends AbstractController {

    protected final String name = "city";
    
    @Autowired
    private CityService cityService;

    @PostConstruct
    protected void init(){
        generalService = cityService;
        objectName = name;
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveCity(@AuthenticationPrincipal User user,
                                  City city){

        cityService.save(city);
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        City city = cityService.findById(id);
        cityService.delete(city);
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }
}
