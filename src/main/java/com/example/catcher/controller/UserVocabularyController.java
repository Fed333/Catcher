package com.example.catcher.controller;

import com.example.catcher.domain.ProgressWord;
import com.example.catcher.domain.User;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user_vocabulary")
public class UserVocabularyController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userVocabulary(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "English", name="languageFilter") String languageFilter,
            @RequestParam(required = false, defaultValue = "", name="wordFilter") String wordFilter,
            @RequestParam(required = false, defaultValue = "off", name="a1") String a1,
            @RequestParam(required = false, defaultValue = "off", name="a2") String a2,
            @RequestParam(required = false, defaultValue = "off", name="b1") String b1,
            @RequestParam(required = false, defaultValue = "off", name="b2") String b2,
            @RequestParam(required = false, defaultValue = "table", name = "view") String view,
            Model model
    ){

        List<ProgressWord> vocabulary = userService.search(user, languageFilter, wordFilter, a1, a2, b1, b2);

        model.addAttribute("user", user);
        model.addAttribute("view", view);
        model.addAttribute("vocabulary", vocabulary);
        model.addAttribute("languageFilter", languageFilter);
        model.addAttribute("wordFilter", wordFilter);
        model.addAttribute("a1", a1);
        model.addAttribute("a2", a2);
        model.addAttribute("b1", b1);
        model.addAttribute("b2", b2);

        return "userVocabulary";
    }
}
