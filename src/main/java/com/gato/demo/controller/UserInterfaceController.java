package com.gato.demo.controller;

import com.gato.demo.model.User;
import com.gato.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PostRemove;
import java.util.Objects;

@Controller
public class UserInterfaceController{
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/")
    public String index()
    {
        return"index";
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(@ModelAttribute User user) {
        if (userRepository.findByUsername(user.getUsername()).size()!=0 || userRepository.findByEmail(user.getEmail()).size()!=0){
            return "redirect:/";
        }
        else {
            userRepository.save(user);
            return "redirect:/";
        }
    }

    @PostMapping(value="/loginUser")
    public ModelAndView loginUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userRepository.findByUsername(user.getUsername()).size()!=0 &&
                Objects.equals(userRepository.findByUsername(user.getUsername()).get(0).getPassword(), user.getPassword())){
            modelAndView.addObject("user", user);
            modelAndView.setViewName("home");
            return modelAndView;
        }
        else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
    }

    @GetMapping(value = "/home")
    public ModelAndView gohome(@ModelAttribute User user) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("home");
            return modelAndView;
    }

    @GetMapping(value = "/profile")
    public ModelAndView goprofile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @GetMapping(value = "/myServer")
    public ModelAndView gomyServer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("myServer");
        return modelAndView;
    }

}
