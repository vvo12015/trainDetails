package net.vrakin.controller;

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
public class CityController extends AbstractController {

    protected final String name = "city";
    
    @Autowired
    protected GeneralService<City> cityService;

    @PostConstruct
    protected void init(){
        objectName = name;
        generalService = cityService;
        model.put("fields", City.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveCity(@AuthenticationPrincipal User user,
                                  City city){

        if (generalService.checkUniqueName(city.getName())){
            errors.add("Тhe name is not unique");
        }else {
            generalService.save(city);
        }
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
