package net.vrakin.controller;

import net.vrakin.model.DetailMuseum;
import net.vrakin.model.User;
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
public class DetailsMuseumController extends AbstractController {

    protected final String name = "detailsMuseum";

    @Autowired
    private GeneralService<DetailMuseum> detailMuseumService;

    @PostConstruct
    protected void init(){
        generalService = detailMuseumService;
        objectName = name;
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveDetailMuseum(@AuthenticationPrincipal User user,
                                  DetailMuseum detailMuseum){

        detailMuseumService.save(detailMuseum);
        setModelList(user);
        return new ModelAndView("admin_table", model);
    }

    @GetMapping("/" + name + "_remove/{id}")
    public ModelAndView delete(@AuthenticationPrincipal User user,
                               @PathVariable("id") Long id){
        DetailMuseum detailMuseum = detailMuseumService.findById(id);
        detailMuseumService.delete(detailMuseum);
        setModelList(user);

        return new ModelAndView("admin_table", model);
    }
}
