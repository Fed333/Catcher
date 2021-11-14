package com.example.catcher.repos;

import com.example.catcher.domain.ProgressWord;
import com.example.catcher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressWordRepo extends JpaRepository<ProgressWord, Long> {
    @Override
    List<ProgressWord> findAll();
    @Override
    Optional<ProgressWord> findById(Long id);

    Optional<List<ProgressWord>> findByUserId(Long id);

}
