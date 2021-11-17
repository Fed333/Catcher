package com.example.catcher.dto;

import com.example.catcher.domain.TestQuestion;

import java.util.List;

public class Task1QuestionsRequest {
    private List<TestQuestion> task1;

    public List<TestQuestion> getTask1() {
        return task1;
    }

    public void setTask1(List<TestQuestion> task1) {
        this.task1 = task1;
    }

    @Override
    public String toString(){
        return "Task1QuestionsRequest [tests=" + task1 + "]";
    }
}
