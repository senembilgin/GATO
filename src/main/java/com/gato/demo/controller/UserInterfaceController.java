package com.gato.demo.controller;
import com.gato.demo.model.Server;
import com.gato.demo.model.User;
import com.gato.demo.repository.ServerRepository;
import com.gato.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Objects;

@Controller
public class UserInterfaceController{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServerRepository serverRepository;
    Server gamerServer = new Server("gamer");
    Server lifeServer = new Server("life");
    User user = new User();


    @RequestMapping("/")
    public String index()
    {

        return"index";
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(@ModelAttribute User user) {
        if (userRepository.findByUsername(user.getUsername()).size()!=0 || userRepository.findByEmail(user.getEmail()).size()!=0){
            //error
            return "redirect:/";
        }
        else {
            this.user = user;
            userRepository.save(this.user);
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


    @PostMapping("/addUserToGamer")
    public ModelAndView addUserToGamer() {
        serverRepository.save(gamerServer);
        user.getServerList().add(gamerServer);
        gamerServer.getUserList().add(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping("/addUserToLife")
    public ModelAndView addUserToLife() {
        serverRepository.save(lifeServer);
        user.getServerList().add(lifeServer);
        lifeServer.getUserList().add(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping("/removeUserFromGamer")
    public ModelAndView removeUserFromGamer(){
        serverRepository.save(gamerServer);
        gamerServer.getUserList().remove(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping("/removeUserFromLife")
    public ModelAndView removeUserFromLife(){
        serverRepository.save(lifeServer);
        lifeServer.getUserList().remove(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
