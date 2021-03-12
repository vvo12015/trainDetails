package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.City;
import net.vrakin.model.ShowContentsInList;
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

@Controller
@Slf4j
public class CityController extends AbstractController {

    protected final String name = "city";
    
    @Autowired
    protected GeneralService<City> cityService;

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        objectName = name;
        generalService = cityService;
        model.put("fields", City.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){
        log.debug("call method: toList with user" + user.getUsername());
        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveCity(@AuthenticationPrincipal User user,
                                  City city){
        log.debug("call method: saveCity with user and city");
        log.debug("user: " + user.getUsername());
        if (generalService.checkUniqueName(city.getName())){
            errors.add("Тhe name is not unique");
            log.debug("Тhe name is not unique");
        }else {
            generalService.save(city);
            log.debug("save cargo with name " + city.getName() + " successfully");

        }
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        log.debug("call method: delete with user and id");
        log.debug("user: " + user.getUsername());
        City city = cityService.findById(id);
        cityService.delete(city);
        setModelList(user);
        log.debug("delete city: cityId - " + city.getId() + " with name " + city.getName() + " successfully");
        return new ModelAndView("admin_table", model);
    }
}
