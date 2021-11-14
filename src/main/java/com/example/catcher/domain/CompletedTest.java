package com.example.catcher.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="completed_tests")
public class CompletedTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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
}
