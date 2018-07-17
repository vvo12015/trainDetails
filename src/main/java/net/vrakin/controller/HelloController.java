package net.vrakin.controller;

import net.vrakin.model.Role;
import net.vrakin.model.TrainMuseum;
import net.vrakin.model.User;
import net.vrakin.repository.TrainMuseumRepository;
import net.vrakin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private TrainMuseumRepository trainMuseumRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public ModelAndView index(){
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = (List<TrainMuseum>) trainMuseumRepository.findAll();

        return new ModelAndView("index", model);
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User existing!!!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/train_museum")
    public ModelAndView train_museum(){
        Map<String, Object> model = new HashMap<>();
        List<TrainMuseum> trainMuseum = (List<TrainMuseum>) trainMuseumRepository.findAll();

        model.put("trains", trainMuseum);
        return new ModelAndView("train_museum", model);
    }
}
