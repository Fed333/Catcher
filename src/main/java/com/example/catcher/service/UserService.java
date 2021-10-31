package com.example.catcher.service;

import com.example.catcher.domain.Role;
import com.example.catcher.domain.User;
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
import java.util.Collections;
import java.util.UUID;

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
}
