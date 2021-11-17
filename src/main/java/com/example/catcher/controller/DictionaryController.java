package com.example.catcher.controller;

import com.example.catcher.domain.Level;
import com.example.catcher.domain.ProgressWord;
import com.example.catcher.domain.User;
import com.example.catcher.domain.Word;
import com.example.catcher.service.ProgressWordService;
import com.example.catcher.service.UserService;
import com.example.catcher.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProgressWordService progressWordService;

    @Value("${words.path}")
    private String wordsPath;

    @GetMapping
    public String dictionary(
            @RequestParam(required = false, defaultValue = "English", name="languageFilter") String languageFilter,
            @RequestParam(required = false, defaultValue = "", name="wordFilter") String wordFilter,
            @RequestParam(required = false, defaultValue = "off", name="a1") String a1,
            @RequestParam(required = false, defaultValue = "off", name="a2") String a2,
            @RequestParam(required = false, defaultValue = "off", name="b1") String b1,
            @RequestParam(required = false, defaultValue = "off", name="b2") String b2,
            @RequestParam(required = false, defaultValue = "None", name="sortCriterion") String sortCriterion,
            @RequestParam(required = false, defaultValue = "Asc", name="sortOrder") String sortOrder,
            @RequestParam(required = false, defaultValue = "false", name="displayAddForm") String showAddForm,
            @AuthenticationPrincipal User user,
            Model model
    ){

        List<Word> words = wordService.searchWords(languageFilter, wordFilter, a1, a2, b1, b2, sortCriterion, sortOrder);

        List<ProgressWord> vocabulary = userService.getVocabulary(user);
        LinkedList<Long> words_id = progressWordService.extractWordsId(vocabulary);

        String strArr = words_id.toString();

        model.addAttribute("words", words);
        model.addAttribute("data_id", strArr);
        model.addAttribute("languageFilter", languageFilter);
        model.addAttribute("wordFilter", wordFilter);
        model.addAttribute("a1", a1);
        model.addAttribute("a2", a2);
        model.addAttribute("b1", b1);
        model.addAttribute("b2", b2);
        model.addAttribute("sortCriterion", sortCriterion);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("showAddForm", showAddForm);

        return "dictionary";
    }

    @GetMapping("{word}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String wordEditForm(
            @PathVariable Word word,
            Model model
    ){
        model.addAttribute("word", word);
        return "wordEdit";
    }
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String editWord(
            @RequestParam("wordId") Word word,
            @RequestParam(name="word") String meaning,
            @RequestParam(name="translation") String translation,
            @RequestParam(name="level") Level level,
            @RequestParam(name="wordImage") MultipartFile file,

            Model model
    ){
        if (!wordService.update(word, meaning, translation, level, file)){
            model.addAttribute("message", "Не коректно заповненні поля");
        }
        return "redirect:/dictionary?a1=on&a2=on&b1=on&b2=on";
    }

    @PostMapping
    public String addWord(
        @RequestParam(name="word") String meaning,
        @RequestParam(name="translation") String translation,
        @RequestParam(name="level") Level level,
        @RequestParam(name="wordImage") MultipartFile imgFile,
        Model model
    ) throws IOException {
        Word word = wordService.createWord(meaning, translation, level, imgFile);
        if (word != null){
            if (!wordService.addWord(word)) {
                model.addAttribute("message", "Слово " + word.getWord() + " вже присутнє в базі даних");
            }
        }
        else{
            model.addAttribute("message", "Некоректно заповненні поля");
        }

        return "redirect:/dictionary?a1=on&a2=on&b1=on&b2=on&displayAddForm=true";
    }

    @PostMapping("/add_to_user_vocabulary")
    public String addWordToUserVocabulary(
            @AuthenticationPrincipal User user,
            @RequestParam(name="wordId") Word word
    )
    {

        if (userService.learnWord(user, word)) {
            System.out.println("Додано нове слово: " + word.getWord());
        }
        else{
            System.out.println("Спроба додати вже вивчене слово: " + word.getWord());
        }
        return "redirect:/dictionary?a1=on&a2=on&b1=on&b2=on";
    }
}
