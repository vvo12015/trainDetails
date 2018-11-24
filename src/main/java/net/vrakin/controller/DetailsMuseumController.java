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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DetailsMuseumController extends AbstractController {

    protected final String name = "detail_museum";

    @Autowired
    private GeneralService<DetailMuseum> detailMuseumService;

    @PostConstruct
    protected void init(){
        objectName = name;
        generalService = detailMuseumService;
        model.put("fields", DetailMuseum.getFields());
    }

    @GetMapping("/" + name)
    public ModelAndView toList(@AuthenticationPrincipal User user){

        setModelList(user);
        createListMap();
        return getModelAndView();
    }

    @PostMapping("/" + name)
    public ModelAndView saveDetailMuseum(@AuthenticationPrincipal User user,
                                  DetailMuseum detailMuseum){

        if (generalService.checkUniqueName(detailMuseum.getName())){
            errors.add("Тhe name is not unique");
        }else {
            generalService.save(detailMuseum);
        }
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

    @Override
    protected void createListMap() {

        Map<String, String> valueTrue = new HashMap<>();
        valueTrue.put("id", "1");
        valueTrue.put("name", "true");
        Map<String, String> valueFalse = new HashMap<>();
        valueFalse.put("id", "0");
        valueFalse.put("name", "false");
        List<Map<String, String>> listIsRepaired = new ArrayList<>();

        listIsRepaired.add(valueTrue);
        listIsRepaired.add(valueFalse);
        listMap.put("isRepaired", listIsRepaired);

        super.createListMap();
    }
}
