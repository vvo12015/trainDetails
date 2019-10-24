package net.vrakin.controller;

import net.vrakin.model.GameConfig;
import net.vrakin.model.User;
import net.vrakin.service.GameConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
public class GameConfigController extends AbstractController {

    @Autowired
    private GameConfigService gameConfigService;

    protected final String name = "gameConfig";

    @PostConstruct
    protected void init(){
        generalService = gameConfigService;
        objectName = name;
        model.put("fields", GameConfig.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView save(@AuthenticationPrincipal User user,
                                         GameConfig gameConfig){
        generalService.save(gameConfig);

        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                                           @PathVariable("id") Long id){
        GameConfig gameConfig = (GameConfig) generalService.findById(id);
        generalService.delete(gameConfig);
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }


}
