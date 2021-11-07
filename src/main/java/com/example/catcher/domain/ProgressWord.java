package com.example.catcher.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="progress_word")
public class ProgressWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="word_id")
    private Word word;

    @Column(name="learned_date")
    private Date learnedDate;

    @Column(name="last_revision_date")
    private Date lastRevisionDate;

    //загальна кількість повторенб
    @Column(name="revision_count")
    private Integer revisionCount;

    //кількість успішних повторень
    @Column(name="guessing_count")
    private Integer guessingCount;

    //чи слово було вивченим
    @Column(name="studied")
    private Boolean studied;

    private ProgressWord() {
        revisionCount = 0;
        guessingCount = 0;
        learnedDate = new Date();
        studied = false;
    }

    public ProgressWord(Word word, Date learnedDate) {
        this();
        this.word = word;
        this.learnedDate = learnedDate;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }


    public Date getLearnedDate() {
        return learnedDate;
    }

    public void setLearnedDate(Date learnedDate) {
        this.learnedDate = learnedDate;
    }

    public Date getLastRevisionDate() {
        return lastRevisionDate;
    }

    public void setLastRevisionDate(Date lastRevisionDate) {
        this.lastRevisionDate = lastRevisionDate;
    }

    public Integer getRevisionCount() {
        return revisionCount;
    }

    public void setRevisionCount(Integer revisionCount) {
        this.revisionCount = revisionCount;
    }

    public Integer getGuessingCount() {
        return guessingCount;
    }

    public void setGuessingCount(Integer guessingCount) {
        this.guessingCount = guessingCount;
    }

    public Boolean getStudied() {
        return studied;
    }

    public void setStudied(Boolean studied) {
        this.studied = studied;
    }

    public Long getId() {
        return id;
    }
}
