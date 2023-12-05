package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.Constants;
import com.dictionaryapp.model.enums.LanguageNameENUM;
import com.dictionaryapp.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.dictionaryapp.model.enums.Constants.*;
import static com.dictionaryapp.model.enums.LanguageNameENUM.*;

@Component
public class LanguageInit implements CommandLineRunner {

    private final LanguageService languageService;

    @Autowired
    public LanguageInit(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (languageService.getRepoCount() == 0) {
            Arrays.stream(LanguageNameENUM.values())
                    .forEach(currLanguageNameENUM -> {
                        Language language = new Language();
                        language.setLanguageNameENUM(currLanguageNameENUM);

                        switch (currLanguageNameENUM) {
                            case SPANISH:
                                language.setDescription(SPANISH_DESCRIPTION);
                                break;
                            case FRENCH:
                                language.setDescription(FRENCH_DESCRIPTION);
                                break;
                            case GERMAN:
                                language.setDescription(GERMAN_DESCRIPTION);
                                break;
                            case ITALIAN:
                                language.setDescription(ITALIAN_DESCRIPTION);
                                break;

                        }
                        languageService.saveLanguage(language);

                    });
        }
    }
}
