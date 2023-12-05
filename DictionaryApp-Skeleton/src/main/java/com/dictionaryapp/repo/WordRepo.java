package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageNameENUM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepo extends JpaRepository<Word, Long> {

    List<Word> getWordByLanguageLanguageNameENUM(LanguageNameENUM languageNameENUM);


    List<Word> getWordByUserId(Long id);
}
