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
    public List<TestQuestion> getTestQuestion(CompletedTest completedTest){
        if (completedTest == null){
            return null;
        }
        List<TestQuestion> questions;
        try{
            if (!Hibernate.isInitialized(completedTest)) {
                Hibernate.initialize(completedTest);
            }
            questions = completedTest.getQuestions();
            if (!Hibernate.isInitialized(questions)){
                Hibernate.initialize(questions);
            }
        }
        catch(LazyInitializationException le){
            System.out.println("still caught lazyInitializationException");
            questions = testQuestionRepo.findAllByTestId(completedTest.getId());
        }
        return questions;

    }

    public int getTotalTestScore(CompletedTest completedTest) {
        List<TestQuestion> tests = getTestQuestion(completedTest);
        int totalScore = 0;
        for (TestQuestion tq: tests) {
            totalScore += tq.getPoints();
        }
        return totalScore;
    }

    //повертає точність, число від 0 до 1
    public double getTestAccuracy(CompletedTest completedTest) {
        List<TestQuestion> list = getTestQuestion(completedTest);
        double accuracy = 0;
        for (TestQuestion tq: list) {
            accuracy += tq.getSimilarity();
        }
        accuracy = accuracy/(100*list.size());
        return accuracy;
    }
}
