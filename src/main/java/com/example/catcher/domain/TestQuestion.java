package com.example.catcher.domain;

import javax.persistence.*;

@Entity
@Table(name = "test_questions")
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private CompletedTest test;

    @Column(name="question")
    private String question;

    @Column(name="answer")
    private String answer;

    @Column(name = "points")
    private Integer points;

    public TestQuestion() {
        points = 0;
    }

    public TestQuestion(String question, String answer) {
        this();
        this.question = question;
        this.answer = answer;
    }

    public CompletedTest getTest() {
        return test;
    }

    public void setTest(CompletedTest test) {
        this.test = test;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
