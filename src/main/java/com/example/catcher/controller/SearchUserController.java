package com.example.catcher.controller;

import com.example.catcher.domain.Level;
import com.example.catcher.domain.User;
import com.example.catcher.dto.LevelsRequest;
import com.example.catcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user_search")
public class SearchUserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userSearch(
            @RequestParam(name="login", required = false, defaultValue = "") String login,
            @RequestParam(name="name", required = false, defaultValue = "") String name,
            @RequestParam(name="phone", required = false, defaultValue = "") String phone,
            @RequestParam(name="email", required = false, defaultValue = "") String email,
            LevelsRequest levelsRequest,
            @RequestParam(name="showCollapse", required = false, defaultValue = "false") String filterSearch,

            Model model
    )
    {
        List<User> foundUsers = userService.searchUsersBy(login, filterSearch.equalsIgnoreCase("true"), name, phone, email, levelsRequest.getLevelsEnum());
        if (foundUsers != null && !foundUsers.isEmpty()){
            model.addAttribute("users", foundUsers);
        }
        if (levelsRequest.getLevels() != null) {
            model.addAttribute("selectedLevels", levelsRequest.getLevels().toString());
        }

        model.addAttribute("login", login);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("email", email);
        model.addAttribute("showCollapse", filterSearch);

        return "userSearch";
    }
}
