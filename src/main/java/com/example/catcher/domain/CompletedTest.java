package com.example.catcher.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="completed_tests")
public class CompletedTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TestQuestion> questions;


    @Column(name="user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "taking_time")
    private Date takingTime;

    @Column(name = "score")
    private Integer score;


    public CompletedTest(){
        takingTime = new Date();
    }

    public CompletedTest(User user, Integer score) {
        this();
        this.user = user;
        this.userId = user.getId();
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(Date takingTime) {
        this.takingTime = takingTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public List<TestQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestQuestion> questions) {
        this.questions = questions;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
