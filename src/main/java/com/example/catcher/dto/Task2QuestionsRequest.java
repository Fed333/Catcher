package com.example.catcher.dto;

import com.example.catcher.domain.TestQuestion;

import java.util.List;

public class Task2QuestionsRequest {
    private List<TestQuestion> task2;

    public List<TestQuestion> getTask2() {
        return task2;
    }

    public void setTask2(List<TestQuestion> task2) {
        this.task2 = task2;
    }

    @Override
    public String toString(){
        return "Task2QuestionsRequest [tests=" + task2 + "]";
    }
}
