package com.example.catcher.service;

import com.example.catcher.domain.Level;
import com.example.catcher.domain.ProgressWord;
import com.example.catcher.domain.User;
import com.example.catcher.repos.ProgressWordRepo;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProgressWordService {
    @Autowired
    private ProgressWordRepo progressWordRepo;

    @Autowired
    private UserService userService;


    public LinkedList<Long> extractWordsId(List<ProgressWord> vocabulary) {
        LinkedList<Long> wordsId = new LinkedList<>();
        vocabulary.forEach(pw->{
            wordsId.add(pw.getWord().getId());
        });
        return wordsId;
    }

    private List<ProgressWord> searchUserVocabularyBy(User user, String filter, Set<Level> levelsFilter, WordService.WordAttributeCriterion criterion){

        LinkedList<ProgressWord> found = new LinkedList<>();
        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        Iterable<ProgressWord> vocabulary = this.getVocabulary(user);
        for(ProgressWord pw: vocabulary){
            Matcher matcher = pattern.matcher(criterion.byCriterion(pw.getWord()));
            if (levelsFilter.contains(pw.getWord().getLevel()) && matcher.find()){
                found.add(pw);
            }
        }
        return found;
    }

    public List<ProgressWord> getVocabulary(User user) {
        List<ProgressWord> voc;
        try {
            voc = user.getVocabulary();
            if (!Hibernate.isInitialized(voc)) {
                Hibernate.initialize(voc);
            }
        } catch (LazyInitializationException e) {
            User userFetched = userService.findUserById(user.getId());
            voc = userFetched.getVocabulary();
        }

        return voc;
    }

}
