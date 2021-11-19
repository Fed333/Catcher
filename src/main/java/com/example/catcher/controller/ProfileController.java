package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(
//            прийме якщо буде модель з іншого контроллера при переході
            @ModelAttribute("warningModel") Object warningModel,
            @AuthenticationPrincipal User user,     //дістане користувача з яким відбувається сесія
            Model model
    ){

        model.addAttribute("user", user);

        if (userService.getVocabulary(user).size() < TestController.numberOfTests){
            model.addAttribute("disableTestLink", true);
        }

        if (warningModel instanceof Model) {
            model.addAttribute("warning", ((Model) warningModel).getAttribute("warning"));
        }
        //ось цьою штукою можна видалити атрибут із моделі
        model.asMap().remove("warningModel");

        return "profile";
    }

    @PostMapping
    public String changeProfile (
            @AuthenticationPrincipal User user,
            @RequestParam(name="login") String login,
            @RequestParam(name="name") String name,
            @RequestParam(name="email") String email,
            @RequestParam(name="phone") String phone,
            @RequestParam(name="birthday") String birthday,
            @RequestParam(name="file") MultipartFile file
    ) throws IOException
    {
        userService.updateProfile(user, login, name, email, phone, birthday, file);

        return "redirect:/profile";
    }
}
