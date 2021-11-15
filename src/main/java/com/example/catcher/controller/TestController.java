package com.example.catcher.controller;

import com.example.catcher.domain.CompletedTest;
import com.example.catcher.domain.TestQuestion;
import com.example.catcher.domain.User;
import com.example.catcher.domain.Word;
import com.example.catcher.dto.Task1QuestionsRequest;
import com.example.catcher.dto.Task2QuestionsRequest;
import com.example.catcher.service.CompletedTestService;
import com.example.catcher.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private Integer numberOfTests = 5;
    @Autowired
    private UserService userService;


    @Autowired
    private CompletedTestService completedTestService;

    @GetMapping
    public String testPage(
            @AuthenticationPrincipal User user,
            Model model
    ){
        List<Word> learningWord = userService.getLearnedWords(user, numberOfTests);    //поверне 5 рандомних слів зі словника user
        model.addAttribute("task1", learningWord);
        return "test";
    }

    @PostMapping
    @Transactional(readOnly = true)
    public String checkTest(

//            String[] question,
//            String[] answer,
//            @RequestBody(required = false) TestQuestionsRequest question,
            Task1QuestionsRequest task1,
            Task2QuestionsRequest task2,

            @AuthenticationPrincipal User user,
            Model model
    )
    {
        userService.checkTask1(user, task1);
        model.addAttribute("user", user);
        return "redirect:/profile";
    }


}
