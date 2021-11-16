package com.example.catcher;

import com.example.catcher.algorithms.BinarySearch;
import com.example.catcher.algorithms.Sorts;
import com.example.catcher.domain.Word;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
//        List<Word> words = new ArrayList<>();
//        words.add(new Word("to watch out", "остерігатися, берегтися"));
//        words.add(new Word("impolite", "невічливий"));
//        words.add(new Word("a contribution", "внесок"));
//        words.add(new Word("overtime", "понаднормовий"));
//
//        words = Sorts.qSort(words, new Word.TranslationComparator());
//
//        Word word = new Word("impolite", "невічливий");
//
//        int index = BinarySearch.binarySearch((ArrayList<Word>)words, word, new Word.TranslationComparator());
        SpringApplication.run(Application.class, args);
    }
}
