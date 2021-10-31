package com.example.catcher.domain;

import javax.persistence.*;

@Entity
@Table(name="dictionary")
public class Word implements Comparable<Word> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="word", unique = true, nullable = false)
    private String word;

    @Column(name="translation", nullable = false)
    private String translation;

    @Column(name="level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name="img_name")
    private String imgName;

    public enum Criterion{
       WORD, TRANSLATION, LEVEL
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.getWord());
    }
}


