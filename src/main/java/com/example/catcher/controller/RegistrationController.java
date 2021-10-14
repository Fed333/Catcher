package com.example.catcher.controller;

import com.example.catcher.domain.Role;
import com.example.catcher.domain.User;
import com.example.catcher.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(
            User user,
            Model model
    ){
        User userFromDb = userRepo.findByLogin(user.getUsername());
        if (userFromDb != null){
            return "registration";
        }
        else {
            user.setRoles(Collections.singleton(Role.STUDENT));
            userRepo.save(user);
        }

        return "redirect:/login";
    }
}
