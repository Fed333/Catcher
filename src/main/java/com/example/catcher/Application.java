package com.example.catcher;

import com.example.catcher.algorithms.BinarySearch;
import com.example.catcher.algorithms.EditorialDistance;
import com.example.catcher.algorithms.Sorts;
import com.example.catcher.domain.Word;
import com.sun.xml.bind.v2.util.EditDistance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
