package com.example.catcher.domain;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

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

    public Word() {
    }

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

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

    public static class TranslationComparator implements Comparator<Word> {
        @Override
        public int compare(Word o1, Word o2) {
            return o1.getTranslation().compareTo(o2.getTranslation());
        }
    }

    public static class WordComparator implements Comparator<Word> {
        @Override
        public int compare(Word o1, Word o2) {
            return o1.getWord().compareTo(o2.getWord());
        }
    }

    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.getWord());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        return Objects.equals(id, word1.id) &&
                Objects.equals(word, word1.word) &&
                Objects.equals(translation, word1.translation) &&
                level == word1.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translation, level);
    }


    @Override
    public String toString() {
        return String.format("%20s, %20s, %3s", word, translation, level);
    }
}


