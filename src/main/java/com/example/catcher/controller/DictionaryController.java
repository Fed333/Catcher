package com.example.catcher.controller;

import com.example.catcher.domain.Word;
import com.example.catcher.repos.WordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictionaryController {
    @Autowired
    private WordRepo wordRepo;

    @GetMapping("/dictionary")
    public String dictionary(Model model){
        Iterable<Word> words = wordRepo.findAll();
        model.addAttribute("words", words);
        return "dictionary";
    }
}
