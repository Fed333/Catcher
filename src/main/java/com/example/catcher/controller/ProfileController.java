package com.example.catcher.controller;

import com.example.catcher.domain.User;
import com.example.catcher.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepo userRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String profile(
            @AuthenticationPrincipal User user,     //дістане користувача з яким відбувається сесія
            Model model
    ){
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping
    public String changeProfile (
            @AuthenticationPrincipal User user,
            @RequestParam(name="file") MultipartFile file,
            @RequestParam(name="login") String login,
            @RequestParam(name="name") String name,
            @RequestParam(name="email") String email,
            @RequestParam(name="phone") String phone,
            @RequestParam(name="birthday") String birthday
    ) throws IOException
    {
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
                //створюємо унікальне ім'я файлу
                String uniqueName = UUID.randomUUID().toString();
                uniqueName += "." + file.getOriginalFilename();

                //завантажуємо файл
                file.transferTo(new File(uploadPath + "/" + uniqueName));
                user.setAvatarName(uniqueName);
            }

            userRepo.save(user);
        }
        return "redirect:/profile";
    }
}
