package net.vrakin.controller;

import net.vrakin.model.Cargo;
import net.vrakin.model.User;
import net.vrakin.service.CargoService;
import net.vrakin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    protected final String name = "users";

    @PostConstruct
    protected void init(){
        generalService = userService;
        objectName = name;
        model.put("fields", User.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    /*@PostMapping("/" + name)
    public ModelAndView saveCargo(@AuthenticationPrincipal User user,
                                         User users){
        if (generalService.checkUniqueName(users.getName())){
            errors.add("Тhe name is not unique");
        }else {
            generalService.save(users);
        }
        setModelList(users);
        return new ModelAndView("admin_table", model);
    }*/

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        User currentUser = (User) generalService.findById(id);
        generalService.delete(currentUser);
        setModelList(currentUser);

        return new ModelAndView("admin_table", model);
    }


}
