package com.example.demoTwitter.controller;

import com.example.demoTwitter.model.User;
import com.example.demoTwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// the controller is the navigation of what the user can do. every thing form login signup and write a comment. This controller is handling the login and the sighup as of right now.
@Controller
public class AuthorizationController {

    @Autowired
    private UserService userService;
    // Here is the login method
    @GetMapping(value="/login")
    public String login(){
        return "login";
    }
    // her is the get signup method
    @GetMapping(value="/signup")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }
    // the post method allows the user to submit a form that the user needs to fill out to have a account. This method also checks if the user name already exists
    @PostMapping(value = "/signup")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already taken");
        }
        if (!bindingResult.hasErrors()) {
            userService.saveNewUser(user);
            model.addAttribute("success", "Sign up successful!");
            model.addAttribute("user", new User());
        }
        return "registration";
    }
}