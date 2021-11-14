package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.domain.Word;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String testPage(
            @AuthenticationPrincipal User user,
            Model model
    ){
        List<Word> learningWord = userService.getLearnedWords(user, 5);    //поверне 5 рандомних слів зі словника user
        model.addAttribute("task1", learningWord);
        return "test";
    }
}
