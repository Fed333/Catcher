package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String head(@RequestParam(required = false, defaultValue="") String filter, Map<String, Object> model){
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()){
            users = userRepo.findAllByLogin(filter);
        }
        else{
            users= userRepo.findAll();
        }
        model.put("users", users);
        model.put("filter", filter);
        return "main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue="") String filter, Map<String, Object> model){
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()){
            users = userRepo.findAllByLogin(filter);
        }
        else{
            users= userRepo.findAll();
        }
        model.put("users", users);
        model.put("filter", filter);
        return "main";
    }




}
