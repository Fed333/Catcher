package com.example.catcher.repos;

import com.example.catcher.domain.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WordRepo extends CrudRepository<Word, Long> {
    Word findByWord(String word);
    Word findByTranslation(String translation);
}
