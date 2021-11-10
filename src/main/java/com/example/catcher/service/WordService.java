package com.example.catcher.service;

import com.example.catcher.algorithms.SortOrder;
import com.example.catcher.algorithms.Sorts;
import com.example.catcher.domain.Level;
import com.example.catcher.domain.Word;
import com.example.catcher.repos.WordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WordService {
    @Autowired
    private WordRepo wordRepo;

    @Value("${words.path}")
    private String wordsPath;

    public Word createWord(String meaning, String translation, Level level, MultipartFile imgFile) throws IOException {
        if (isValid(meaning, translation, level)){
            Word word = new Word();
            word.setWord(meaning);
            word.setTranslation(translation);
            word.setLevel(level);

            if (imgFile != null && imgFile.getOriginalFilename() != null && !imgFile.getOriginalFilename().isEmpty()){
                File uploadDir = new File(wordsPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }
                String uniqueName = UUID.randomUUID().toString() + "." + imgFile.getOriginalFilename();
                word.setImgName(uniqueName);
                imgFile.transferTo(new File(wordsPath + "/" + uniqueName));
            }
            return word;
        }
        return null;
    }

    public boolean addWord(Word word) {
        Word wordFromBd = wordRepo.findByWord(word.getWord());
        if (wordFromBd == null){
            wordRepo.save(word);
            return true;
        }
        return false;
    }

    public boolean update(Word word, String meaning, String translation, Level level, MultipartFile file) {
        if (isValid(meaning, translation, level)) {
            word.setWord(meaning);
            word.setTranslation(translation);
            word.setLevel(level);

            if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
                String uniqueName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                String oldName = word.getImgName();
                //якщо завантажує новий файл
                if (loadFile(file, wordsPath, uniqueName)) {
                    //видаляє старий файл
                    if (oldName != null && !oldName.isEmpty()) {
                        File delFile = new File(wordsPath + "/" + oldName);
                        delFile.delete();
                    }
                    word.setImgName(uniqueName);
                }
            }
            wordRepo.save(word);
            return true;
        }
        return false;
    }

    private boolean loadFile(MultipartFile file, String loadPath, String uniqueName){
        try {
            File uploadDir = new File(loadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(loadPath + "/" + uniqueName));
        }
        catch(IOException e){
            return false;
        }
        return true;
    }

    private boolean isValid(String w, String t, Level l){
        return (w != null && !w.isEmpty() && t != null && !t.isEmpty() && l != null);
    }

    public List<Word> searchWords(String languageFilter, String wordFilter, String a1, String a2, String b1, String b2, String sortCriterion, String sortOrder) {
        List<Word> words = new LinkedList<>();
        Set<Level> levelsFilter = new HashSet<>();

        if (a1.equalsIgnoreCase("on")){
            levelsFilter.add(Level.A1);
        }

        if (a2.equalsIgnoreCase("on")){
            levelsFilter.add(Level.A2);
        }

        if (b1.equalsIgnoreCase("on")){
            levelsFilter.add(Level.B1);
        }

        if (b2.equalsIgnoreCase("on")){
            levelsFilter.add(Level.B2);
        }

        if (wordFilter!= null && !wordFilter.isEmpty()) {
            if (languageFilter.equals("English")) {
                words = searchBy(wordFilter, levelsFilter, Word::getWord);
            }
            else if (languageFilter.equals("Ukrainian")){
                words=searchBy(wordFilter, levelsFilter, Word::getTranslation);
            }
        }
        else{
            Iterable<Word> all = wordRepo.findAll();
            for(Word w: all){
                if (levelsFilter.contains(w.getLevel())){
                    words.add(w);
                }
            }
        }

        try{
            Word.Criterion criterion = Word.Criterion.valueOf(sortCriterion.toUpperCase());
            SortOrder order = SortOrder.valueOf(sortOrder.toUpperCase());
            words = sortRecords(words, criterion, order, languageFilter);
        }
        catch(IllegalStateException e){ }
        catch(Exception e){}

        return words;
    }

    private List<Word> sortRecords(List<Word> records, Word.Criterion criterion, SortOrder order, String languageFilter) {
        if (order.equals(SortOrder.ASC)) {
            if(criterion.equals(Word.Criterion.LEVEL)){
                Comparator<Word> cmp = Comparator.comparing(Word::getLevel);
                records = Sorts.qSort(records, cmp);
            }
            else if (criterion.equals(Word.Criterion.WORD)){
                Comparator<Word> cmp = (languageFilter.equalsIgnoreCase("English"))?Comparator.comparing(Word::getWord):Comparator.comparing(Word::getTranslation);
                records = Sorts.qSort(records, cmp);
            }
            else if(criterion.equals(Word.Criterion.TRANSLATION)){
                Comparator<Word> cmp = (languageFilter.equalsIgnoreCase("Ukrainian"))?Comparator.comparing(Word::getWord):Comparator.comparing(Word::getTranslation);
                records = Sorts.qSort(records, cmp);
            }
        }
        else if(order.equals(SortOrder.DESC)){
            if(criterion.equals(Word.Criterion.LEVEL)){
                Comparator<Word> cmp = (w1, w2)->w2.getLevel().compareTo(w1.getLevel());
                records = Sorts.qSort(records, cmp);
            }
            else if (criterion.equals(Word.Criterion.WORD)){
                Comparator<Word> cmp = (languageFilter.equalsIgnoreCase("English"))?(w1, w2) -> w2.getWord().compareTo(w1.getWord()):(w1, w2) -> w2.getTranslation().compareTo(w1.getTranslation());
                records = Sorts.qSort(records, cmp);
            }
            else if(criterion.equals(Word.Criterion.TRANSLATION)){
                Comparator<Word> cmp = (languageFilter.equalsIgnoreCase("Ukrainian"))?(w1, w2) -> w2.getWord().compareTo(w1.getWord()):(w1, w2) -> w2.getTranslation().compareTo(w1.getTranslation());
                records = Sorts.qSort(records, cmp);
            }
        }
        return records;
    }

    private List<Word> searchBy(String filter, Set<Level> levelsFilter, WordAttributeCriterion criterion){

        LinkedList<Word> found = new LinkedList<>();
        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        Iterable<Word> words = wordRepo.findAll();
        for(Word w: words){
            Matcher matcher = pattern.matcher(criterion.byCriterion(w));
            if (levelsFilter.contains(w.getLevel()) && matcher.find()){
                found.add(w);
            }
        }
        return found;
    }

    private Iterable<Word> searchBy(String filter, FindWordCriterion criterion){
        List<Word> words = new LinkedList<>();
        words.add(criterion.byCriterion(wordRepo, filter));
        return words;
    }

    protected interface FindWordCriterion {
        Word byCriterion(WordRepo wordRepo, String filter);
    }

    protected interface WordAttributeCriterion{
        String byCriterion(Word word);
    }
}
