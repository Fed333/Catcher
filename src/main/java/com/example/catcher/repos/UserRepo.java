package com.example.catcher.repos;

import com.example.catcher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);

    List<User> findAll();
    List<User> findAllByName(String name);
    List<User> findAllByLogin(String login);
}
