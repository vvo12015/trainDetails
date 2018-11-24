package net.vrakin.controller;

import net.vrakin.model.Cargo;
import net.vrakin.model.User;
import net.vrakin.service.CargoService;
import net.vrakin.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
public class CargoController extends AbstractController {

    @Autowired
    private CargoService cargoService;

    protected final String name = "cargo";

    @PostConstruct
    protected void init(){
        generalService = cargoService;
        objectName = name;
        model.put("fields", Cargo.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveCargo(@AuthenticationPrincipal User user,
                                         Cargo cargo){
        if (generalService.checkUniqueName(cargo.getName())){
            errors.add("Тhe name is not unique");
        }else {
            generalService.save(cargo);
        }
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        Cargo cargo = (Cargo) generalService.findById(id);
        generalService.delete(cargo);
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }


}
