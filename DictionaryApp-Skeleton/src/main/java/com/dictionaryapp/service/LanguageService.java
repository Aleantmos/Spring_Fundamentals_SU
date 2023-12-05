package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageNameENUM;
import com.dictionaryapp.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    private final LanguageRepo languageRepo;

    @Autowired
    public LanguageService(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    public long getRepoCount() {
        return languageRepo.count();
    }

    public void saveLanguage(Language language) {
        languageRepo.save(language);
    }

    public Language findByLanguageName(String language) {
        LanguageNameENUM languageNameENUM = LanguageNameENUM.valueOf(language);
        return languageRepo.findLanguageByLanguageNameENUM(languageNameENUM);
    }
}
