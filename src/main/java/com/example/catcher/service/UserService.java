package com.example.catcher.service;

import com.example.catcher.algorithms.BinarySearch;
import com.example.catcher.algorithms.SortOrder;
import com.example.catcher.algorithms.Sorts;
import com.example.catcher.domain.*;
import com.example.catcher.dto.Task1QuestionsRequest;
import com.example.catcher.repos.CompletedTestRepo;
import com.example.catcher.repos.ProgressWordRepo;
import com.example.catcher.repos.TestQuestionRepo;
import com.example.catcher.repos.UserRepo;
import org.aspectj.weaver.ast.Test;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProgressWordRepo progressWordRepo;

    @Autowired
    private CompletedTestRepo completedTestRepo;

    @Autowired
    private TestQuestionRepo testQuestionRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public UserService() {
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByLogin(s);
    }

    public User findUserById(Long id){
        return userRepo.findById(id).get();
    }


    public void updateProfile(User user, String login, String name, String email, String phone, String birthday, MultipartFile file) throws IOException {
        if (user != null){
            user.setLogin(login);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            if (!birthday.isEmpty()) {
                try {
                    user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){   //якщо директорії неіснує
                    uploadDir.mkdir();      //створюємо її
                }
                if (user.getAvatarName() != null && !user.getAvatarName().isEmpty()){
                    File delFile = new File(uploadPath + "/" + user.getAvatarName());
                    delFile.delete();
                }
                //створюємо унікальне ім'я файлу
                String uniqueName = UUID.randomUUID().toString();
                uniqueName += "." + file.getOriginalFilename();

                //завантажуємо файл
                file.transferTo(new File(uploadPath + "/" + uniqueName));
                user.setAvatarName(uniqueName);
            }

            userRepo.save(user);
        }
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByLogin(user.getUsername());
        if (userFromDb != null){
            return false;
        }
        user.setRoles(Collections.singleton(Role.STUDENT));
        userRepo.save(user);
        return true;
    }

    public void save(User user) {
        userRepo.save(user);
    }
//    @Transactional
    public List<ProgressWord> search(User user, String languageFilter, String wordFilter, String a1, String a2, String b1, String b2) {
        List<ProgressWord> vocabulary = new LinkedList<>();
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
                vocabulary = searchBy(user, wordFilter, levelsFilter, Word::getWord);
            }
            else if (languageFilter.equals("Ukrainian")){
                vocabulary=searchBy(user, wordFilter, levelsFilter, Word::getTranslation);
            }
        }
        else{
            Iterable<ProgressWord> all = this.getVocabulary(user);
//            Iterable<ProgressWord> all = progressWordRepo.findByUserId(user.getId());
            for(ProgressWord pw: all){
                if (levelsFilter.contains(pw.getWord().getLevel())){
                    vocabulary.add(pw);
                }
            }
        }
        return vocabulary;
    }

    private List<ProgressWord> searchBy(User user, String filter, Set<Level> levelsFilter, WordService.WordAttributeCriterion criterion){

        LinkedList<ProgressWord> found = new LinkedList<>();
        Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        Iterable<ProgressWord> vocabulary = this.getVocabulary(user);
        for(ProgressWord pw: vocabulary){
            Matcher matcher = pattern.matcher(criterion.byCriterion(pw.getWord()));
            if (levelsFilter.contains(pw.getWord().getLevel()) && matcher.find()){
                found.add(pw);
            }
        }
        return found;
    }

    public boolean learnWord(User user, Word word) {
        List<Long> words = new LinkedList<>();
        List<ProgressWord> vocabulary = this.getVocabulary(user);
        vocabulary.forEach(pw -> {
            words.add(pw.getWord().getId());
        });
        boolean isNewWord = !words.contains(word.getId());
        if (isNewWord){

            ProgressWord pw = new ProgressWord(user, word, new Date());
            //обов'язково зберігати цей об'єкт в бд. Інакше під час сеансу userRepo.save буде штампувати нові
            progressWordRepo.save(pw);
            vocabulary.add(pw);
            userRepo.save(user);

        }
        return isNewWord;
    }

    public User findById(Long id) {
        return userRepo.findById(id).get();
    }


//    @Transactional(readOnly = true)
    public List<ProgressWord> getVocabulary(User user) {
        List<ProgressWord> voc;
        try {
            voc = user.getVocabulary();
            if (!Hibernate.isInitialized(voc)) {
                Hibernate.initialize(voc);
            }
        } catch (LazyInitializationException e) {
            User userFetched = userRepo.getById(user.getId());
            voc = userFetched.getVocabulary();
        }

        return voc;
    }

    public List<Word> getLearnedWords(User user, int number) {
        Random rnd = new Random(System.nanoTime());
        List<Word> learnedWords = user.getWords();
        List<Word> wordToLearn = new LinkedList<>();
        while(number > 0){
            int index = rnd.nextInt(learnedWords.size());
            wordToLearn.add(learnedWords.remove(index));
            --number;
        }
        return wordToLearn;
    }
//    @Transactional
//    завдання 1 - переклад українських слів на англійску
    public void checkTask1(User user, Task1QuestionsRequest task1) {
        List<ProgressWord> vocabulary = getVocabulary(user);

        List<Word> words = new ArrayList<>(vocabulary.size()); //одразу резервуємо пам'ять

        Iterator<ProgressWord> pwIt = vocabulary.iterator();
        while(pwIt.hasNext()){
            ProgressWord pw = pwIt.next();
            words.add(pw.getWord());
        }

        words = Sorts.qSort(words, new Word.TranslationComparator());
        List<TestQuestion> response = task1.getTask1(); //масив із запитаннями та відповідями

        int totalScore = 0;  //рахунок
        CompletedTest test = new CompletedTest();
        Iterator<TestQuestion> rIt = response.iterator();
        while(rIt.hasNext()){ //tq.getQuestion() - питання українські слова, tq.getAnswer() - відповіді англійські
            TestQuestion tq = rIt.next();
            int index = BinarySearch.binarySearch((ArrayList<Word>) words, new Word(tq.getAnswer(), tq.getQuestion()), new Word.TranslationComparator());
            if (index < 0){
                System.out.println("слова з перекладом " + tq.getQuestion() + " серед списку вивчених слів " + user.getLogin() + " не виявлено");
                rIt.remove();
            }
            String answer = words.get(index).getWord(); //відповідь тут англійське слово
            if (answer.toLowerCase().equals(tq.getAnswer().toLowerCase())){
                tq.setPoints(TestQuestion.maxPoints);
                totalScore += tq.getPoints();
            }
            tq.setTest(test);
        }

        user.setScore(user.getScore()+totalScore);

        test.setUser(user);
        test.setScore(totalScore);
        test.setQuestions(response);

        completedTestRepo.save(test);

        test.getQuestions().forEach(q->{
            testQuestionRepo.save(q);
        });

        user.getCompletedTests().add(test);
        userRepo.save(user);
    }
}
