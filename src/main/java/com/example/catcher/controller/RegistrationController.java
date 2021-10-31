package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.repos.UserRepo;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(
            User user,
            Model model
    ){
        if (!userService.addUser(user)){
            return "registration";
        }

        return "redirect:/login";
    }
}
