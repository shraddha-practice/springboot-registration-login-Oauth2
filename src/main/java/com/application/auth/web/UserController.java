package com.application.auth.web;

import com.application.auth.model.Train;
import com.application.auth.model.User;
import com.application.auth.service.*;
import com.application.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private TrainDetailsServiceImpl trainServiceImpl;

    @Autowired
    private TrainService trainService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        else if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }


    @GetMapping("/locations")
    public String locations(Model model) {
        Train[] trains = trainServiceImpl.populateTrainLocations();
        model.addAttribute("trains", trains);
        return "locations";
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam String username, Model model) {
        System.out.println(username);
        User user = userServiceImpl.findByUsername(username);
        model.addAttribute("user", user);
        return "personalInfo";
    }

    @GetMapping("/getTrainInfo")
    public String getTrain(@RequestParam String trainNumber, Model model) {
        Train[] train = trainServiceImpl.getTrainDetails(trainNumber);
        model.addAttribute("trains", train);
        return "trainmap";
    }

}
