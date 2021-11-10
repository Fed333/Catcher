package com.example.catcher.controller;

import com.example.catcher.domain.Level;
import com.example.catcher.domain.Word;
import com.example.catcher.repos.WordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class DictionaryController {
    @Autowired
    private WordRepo wordRepo;

    @GetMapping("/dictionary")
    public String dictionary(
            @RequestParam(
              required = false,
              defaultValue = "English",
              name="languageFilter"
            ) String languageFilter,
            @RequestParam(
                    required = false,
                    defaultValue = "",
                    name="wordFilter"
            ) String wordFilter,
            @RequestParam(
                    required = false,
                    defaultValue = "off",
                    name="a1"
            ) String a1,
            @RequestParam(
                    required = false,
                    defaultValue = "off",
                    name="a2"
            ) String a2,
            @RequestParam(
                    required = false,
                    defaultValue = "off",
                    name="b1"
            ) String b1,
            @RequestParam(
                    required = false,
                    defaultValue = "off",
                    name="b2"
            ) String b2,
            Model model
    ){
        List<Word> words = new LinkedList<>();
        Set<Level> levelsFilter = new HashSet<>();

        if (a1.equalsIgnoreCase("on")){
            levelsFilter.add(Level.A1);
        }

        if (a2.equalsIgnoreCase("on")){
            levelsFilter.add(Level.A2);
        }

        if (b1.equalsIgnoreCase("on")){
            levelsFilter.add(Level.B1);
        }

        if (b2.equalsIgnoreCase("on")){
            levelsFilter.add(Level.B2);
        }

        if (wordFilter!= null && !wordFilter.isEmpty()) {
            if (languageFilter.equals("English")) {
                words = searchBy(wordFilter, levelsFilter, Word::getWord);
            }
            else if (languageFilter.equals("Ukrainian")){
                words=searchBy(wordFilter, levelsFilter, Word::getTranslation);
            }
        }
        else{
            Iterable<Word> all = wordRepo.findAll();
            for(Word w: all){
                if (levelsFilter.contains(w.getLevel())){
                    words.add(w);
                }
            }

        }
        model.addAttribute("words", words);
        model.addAttribute("languageFilter", languageFilter);
        model.addAttribute("wordFilter", wordFilter);
        model.addAttribute("a1", a1);
        model.addAttribute("a2", a2);
        model.addAttribute("b1", b1);
        model.addAttribute("b2", b2);
        return "dictionary";
    }

    private List<Word> searchBy(String filter, Set<Level> levelsFilter, WordAttributeCriterion criterion){

        LinkedList<Word> found = new LinkedList<>();
        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        Iterable<Word> words = wordRepo.findAll();
        for(Word w: words){
            Matcher matcher = pattern.matcher(criterion.byCriterion(w));
            if (levelsFilter.contains(w.getLevel()) && matcher.find()){
                found.add(w);
            }
        }
        return found;
    }

    private Iterable<Word> searchBy(String filter, FindWordCriterion criterion){
        List<Word> words = new LinkedList<>();
        words.add(criterion.byCriterion(wordRepo, filter));
        return words;
    }

    private interface FindWordCriterion {
        Word byCriterion(WordRepo wordRepo, String filter);
    }

    private interface WordAttributeCriterion{
        String byCriterion(Word word);
    }




}
