package com.example.catcher.controller;

import com.example.catcher.domain.CompletedTest;
import com.example.catcher.domain.TestQuestion;
import com.example.catcher.domain.User;
import com.example.catcher.service.CompletedTestService;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test_review")
public class TestReviewController {
    @Autowired
    private UserService userService;

    @Autowired
    private CompletedTestService completedTestService;

    @GetMapping("{completedTest}")
    public String testReviewPage(
            @PathVariable CompletedTest completedTest,
            @AuthenticationPrincipal User user,
            Model model
    )
    {
          List<TestQuestion> task1Review = userService.getTask1Review(user, completedTest);
        if (task1Review!=null){
            int totalScore = completedTestService.getTotalTestScore(completedTest);
            int accuracy = (int)Math.round(completedTestService.getTestAccuracy(completedTest)*100);
            model.addAttribute("task1Review", task1Review);
            model.addAttribute("totalScore", totalScore);
            model.addAttribute("accuracy", accuracy);
        }
        return "testReview";
    }

}
