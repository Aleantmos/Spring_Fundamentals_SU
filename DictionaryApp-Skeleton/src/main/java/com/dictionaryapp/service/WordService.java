package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageNameENUM;
import com.dictionaryapp.repo.WordRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final UserService userService;
    private final WordRepo wordRepo;

    @Autowired
    public WordService(ModelMapper modelMapper, LanguageService languageService, UserService userService, WordRepo wordRepo) {
        this.modelMapper = modelMapper;
        this.languageService = languageService;
        this.userService = userService;
        this.wordRepo = wordRepo;
    }

    public void addWords(AddWordDTO addOfferDTO) {
        Word wordToAdd = modelMapper.map(addOfferDTO, Word.class);

        Language language = languageService.findByLanguageName(addOfferDTO.getLanguage());
        wordToAdd.setLanguage(language);

        User addedBy = userService.findUserById(addOfferDTO.getAddedById());
        wordToAdd.setUser(addedBy);

        wordRepo.save(wordToAdd);
    }

    public List<Word> getWordsForUserInLanguage(LanguageNameENUM languageNameENUM) {
        return wordRepo.getWordByLanguageLanguageNameENUM(languageNameENUM);
    }

    public List<Word> getAllWordsForUser(Long id) {
        return wordRepo.getWordByUserId(id);
    }

    public void removeWordById(Long id) {
        wordRepo.deleteById(id);
    }

    public void removeAll() {
        wordRepo.deleteAll();
    }
}
