package net.vrakin.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CargoController extends AbstractController {

    @Autowired
    private CargoService cargoService;

    protected final String name = "cargo";

    @PostConstruct
    protected void init(){
        log.debug("call method: init");
        generalService = cargoService;
        objectName = name;
        model.put("fields", Cargo.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){
        log.debug("call method: toList with user: " + user.getUsername());
        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveCargo(@AuthenticationPrincipal User user,
                                         Cargo cargo){
        log.debug("call method: saveCargo with user: " + user.getUsername() + "with cargo object");
        if (generalService.checkUniqueName(cargo.getName())){
            errors.add("Тhe name is not unique");
            log.debug("Тhe name is not unique");
        }else {
            generalService.save(cargo);
            log.debug("save cargo with name " + cargo.getName() + " successfully");
        }
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        log.debug("call method: delete with user: " +  user.getUsername() + " and id: " + id);
        Cargo cargo = (Cargo) generalService.findById(id);
        generalService.delete(cargo);
        log.debug("delete cargo with name " + cargo.getName() + " successfully");
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }


}
