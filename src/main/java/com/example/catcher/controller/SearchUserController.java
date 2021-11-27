package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user_search")
public class SearchUserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userSearch(
            @RequestParam(name="login", required = false, defaultValue = "") String login,
            @RequestParam(name="name", required = false, defaultValue = "") String name,
            @RequestParam(name="showCollapse", required = false, defaultValue = "false") String showCollapse,
            Model model
    )
    {
        List<User> foundUsers = userService.searchUsersBy(login, name);
        if (foundUsers != null && !foundUsers.isEmpty()){
            model.addAttribute("users", foundUsers);
        }
        model.addAttribute("login", login);
        model.addAttribute("name", name);
        model.addAttribute("showCollapse", showCollapse);
        return "userSearch";
    }
}
