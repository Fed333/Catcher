package com.example.catcher.repos;

import com.example.catcher.domain.CompletedTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompletedTestRepo extends JpaRepository<CompletedTest, Long> {
    @Override
    List<CompletedTest> findAll();
    @Override
    Optional<CompletedTest> findById(Long id);
    List<CompletedTest> findAllByUserId(Long id);
}
