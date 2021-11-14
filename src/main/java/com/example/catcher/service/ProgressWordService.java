package com.example.catcher.service;

import com.example.catcher.domain.ProgressWord;
import com.example.catcher.repos.ProgressWordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProgressWordService {
    @Autowired
    private ProgressWordRepo progressWordRepo;


    public LinkedList<Long> extractWordsId(List<ProgressWord> vocabulary) {
        LinkedList<Long> wordsId = new LinkedList<>();
        vocabulary.forEach(pw->{
            wordsId.add(pw.getWord().getId());
        });
        return wordsId;
    }

}
