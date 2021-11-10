package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(
            @AuthenticationPrincipal User user,     //дістане користувача з яким відбувається сесія
            Model model
    ){
        model.addAttribute("user", user);
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
