package com.example.catcher.service;

import com.example.catcher.domain.CompletedTest;
import com.example.catcher.domain.ProgressWord;
import com.example.catcher.domain.TestQuestion;
import com.example.catcher.domain.User;
import com.example.catcher.repos.CompletedTestRepo;
import com.example.catcher.repos.TestQuestionRepo;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompletedTestService {

    @Autowired
    private CompletedTestRepo completedTestRepo;

    @Autowired
    private TestQuestionRepo testQuestionRepo;

    @Transactional(readOnly = true)
    public List<TestQuestion> getTestQuestion(CompletedTest test){
        List<TestQuestion> questions;
        try{
            if (!Hibernate.isInitialized(test)) {
                Hibernate.initialize(test);
            }
            questions = test.getQuestions();
            if (!Hibernate.isInitialized(questions)){
                Hibernate.initialize(questions);
            }
        }
        catch(LazyInitializationException le){
            System.out.println("still caught lazyInitializationException");
            questions = testQuestionRepo.findAllByTestId(test.getId());
        }
        return questions;

    }
}
