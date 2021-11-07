package com.example.catcher.service;

import com.example.catcher.algorithms.SortOrder;
import com.example.catcher.domain.*;
import com.example.catcher.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByLogin(s);
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
            Iterable<ProgressWord> all = user.getVocabulary();
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
        Iterable<ProgressWord> vocabulary = user.getVocabulary();
        for(ProgressWord pw: vocabulary){
            Matcher matcher = pattern.matcher(criterion.byCriterion(pw.getWord()));
            if (levelsFilter.contains(pw.getWord().getLevel()) && matcher.find()){
                found.add(pw);
            }
        }
        return found;
    }

    public boolean learnWord(User user, Word word) {
        List<Word> words = new LinkedList<>();
        user.getVocabulary().forEach(pw->{
            words.add(pw.getWord());
        });
        boolean isNewWord = !words.contains(word);
        if (isNewWord){
            user.getVocabulary().add(new ProgressWord(word, new Date()));
        }
        return isNewWord;
    }
}
