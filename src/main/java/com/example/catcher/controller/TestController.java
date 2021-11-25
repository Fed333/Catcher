package com.example.catcher.controller;

import com.example.catcher.domain.CompletedTest;
import com.example.catcher.domain.User;
import com.example.catcher.domain.Word;
import com.example.catcher.dto.Task1QuestionsRequest;
import com.example.catcher.dto.Task2QuestionsRequest;
import com.example.catcher.service.CompletedTestService;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    protected static final Integer numberOfTests = 5;
    @Autowired
    private UserService userService;


    @Autowired
    private CompletedTestService completedTestService;

    @GetMapping
    public String testPage(
            @AuthenticationPrincipal User user,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        List<Word> learningWords = userService.getRandomLearnedWords(user, numberOfTests);    //поверне 5 рандомних слів зі словника user
        if (learningWords != null) {
            model.addAttribute("task1", learningWords);
        }
        else{
            model.addAttribute("warning", String.format("Кількість слів: %d\nНедостатня для проходження тесту!", user.getWords().size()));
            model.addAttribute("disableTaskLink", true);
            redirectAttributes.addFlashAttribute("warningModel", model);
            return "redirect:/profile";
        }
        return "test";
    }

    @PostMapping
    public String checkTest(
            Task1QuestionsRequest task1,
            Task2QuestionsRequest task2,
            @AuthenticationPrincipal User user,
            Model model
    )
    {
        CompletedTest test = userService.checkTask1(user, task1);
        model.addAttribute("user", user);
        return String.format("redirect:/test_review/%d", test.getId());
    }


}
