package com.example.catcher.service;

import com.example.catcher.algorithms.BinarySearch;
import com.example.catcher.algorithms.EditorialDistance;
import com.example.catcher.algorithms.SortOrder;
import com.example.catcher.algorithms.Sorts;
import com.example.catcher.domain.*;
import com.example.catcher.dto.Task1QuestionsRequest;
import com.example.catcher.repos.CompletedTestRepo;
import com.example.catcher.repos.ProgressWordRepo;
import com.example.catcher.repos.TestQuestionRepo;
import com.example.catcher.repos.UserRepo;
import com.sun.istack.NotNull;
import com.sun.xml.bind.v2.util.EditDistance;
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
import java.sql.Timestamp;
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

    public List<Word> getRandomLearnedWords(User user, int number) {
        Random rnd = new Random(System.nanoTime());
        List<Word> learnedWords = user.getWords();
        if (learnedWords.size() < number){
            return null;
        }
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
    public CompletedTest checkTask1(User user, Task1QuestionsRequest task1) {
        List<ProgressWord> vocabulary = new ArrayList<>();

        vocabulary.addAll(getVocabulary(user));

        Comparator<ProgressWord> comparatorTranslation = Comparator.comparing(o -> o.getWord().getTranslation());

        vocabulary = Sorts.qSort(vocabulary, comparatorTranslation);
        List<TestQuestion> response = task1.getTask1(); //масив із запитаннями та відповідями

        int totalScore = 0;  //рахунок
        CompletedTest test = new CompletedTest();
        Iterator<TestQuestion> rIt = response.iterator();
        while(rIt.hasNext()){ //tq.getQuestion() - питання українські слова, tq.getAnswer() - відповіді англійські
            TestQuestion tq = rIt.next();
            int index = BinarySearch.binarySearch((ArrayList<ProgressWord>) vocabulary, new ProgressWord(user, new Word(tq.getAnswer(), tq.getQuestion())), comparatorTranslation);
            if (index < 0){
                System.out.println("слова з перекладом " + tq.getQuestion() + " серед списку вивчених слів " + user.getLogin() + " не виявлено");
                rIt.remove();
                continue;
            }
            ProgressWord progress = vocabulary.get(index);
            progress.setRevisionCount(progress.getRevisionCount()+1);   //додали одне повторення
            //класти туди Timestamp щоб freemarker його розпарсив у шаблоні userVocabulary
            progress.setLastRevisionDate(new Timestamp(new Date().getTime()));                   //оновили дату останнього повторення


            String rightAnswer = progress.getWord().getWord();
            tq.setRightAnswer(rightAnswer);

            //перевірку в нижньому регістрі без артиклів та допоміжних часток
            rightAnswer = rightAnswer.toLowerCase();                    //правильна відповідь тут англійське слово
            String studentRespond = tq.getAnswer().toLowerCase();       //відповідь студента

            rightAnswer = rightAnswer.replaceFirst("(^a |^the |^an |^to )", "");
            studentRespond = studentRespond.replaceFirst("(^a |^the |^an |^to )", "");

            double similarity;
            try {
                EditorialDistance distance = new EditorialDistance(rightAnswer, studentRespond);
                distance.setHashDistances(user.getCache());
                similarity = distance.similarity();              //вирахувати схожість рядків (число від 0 до 1)
            }
            catch(EditorialDistance.OverwhelmedAmountOfMemoryException oe){
                System.out.println(oe.getMessage());
                System.out.println("УВАГА! Буде застосовано звичайне порівняння");
                similarity = rightAnswer.toLowerCase().equals(tq.getAnswer().toLowerCase())?1.0:0.0;
            }
             if (similarity >= TestQuestion.acceptableSimilarity){     //відповідь зараховано якщо схожість вища заданого значення
                tq.setPoints( (int)Math.floor(TestQuestion.maxPoints*similarity));
                totalScore += tq.getPoints();
                progress.setGuessingCount(progress.getGuessingCount()+1);

            }
            progressWordRepo.save(progress);
            tq.setTest(test);
            tq.setSimilarity((int)(similarity*100));
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
        return test;
    }

    public CompletedTest getUsersCompletedTest(User user, Long testId) {
        CompletedTest test = null;
        List<CompletedTest> tests = null;
        try{
           tests = user.getCompletedTests();
        }
        catch(LazyInitializationException le){
            tests = completedTestRepo.findAllByUserId(user.getId());
        }
        if (tests != null) {
            for (CompletedTest t : tests) {
                if (t.getId().equals(testId)) {
                    test = t;
                    break;
                }
            }
        }
        return test;
    }

    public List<TestQuestion> getTask1Review(User user, CompletedTest completedTest) {
        //тест не належить цьому користувачеві
        Long testId = completedTest.getId();
        Long userId = user.getId();
        if (!userId.equals(completedTest.getUserId())){
            return null;
        }
        List<TestQuestion> testQuestions = null;
        try{
            testQuestions = completedTest.getQuestions();
            if (!Hibernate.isInitialized(testQuestions)){
                Hibernate.initialize(testQuestions);
            }
        }
        catch(LazyInitializationException le){
            testQuestions = testQuestionRepo.findAllByTestId(testId);
        }
        return testQuestions;
    }
}
