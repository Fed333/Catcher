package com.example.catcher.repos;

import com.example.catcher.domain.TestQuestion;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

public interface TestQuestionRepo extends JpaRepository<TestQuestion, Long> {
    @Override
    List<TestQuestion> findAll();

    @Override
    Optional<TestQuestion> findById(Long id);

    List<TestQuestion> findAllByTestId(Long id);
}
