package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.dictionaryapp.model.enums.LanguageNameENUM.*;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final WordService wordService;

    @Autowired
    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String loggedOut() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedIn(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }



        List<Word> germanWords = wordService.getWordsForUserInLanguage(GERMAN);
        model.addAttribute("germanWords", germanWords);

        List<Word> frenchWords = wordService.getWordsForUserInLanguage(FRENCH);
        model.addAttribute("frenchWords", frenchWords);

        List<Word> italianWords = wordService.getWordsForUserInLanguage(ITALIAN);
        model.addAttribute("italianWords", italianWords);

        List<Word> spanishWords = wordService.getWordsForUserInLanguage(SPANISH);
        model.addAttribute("spanishWords", spanishWords);

        List<Word> allWordsForUser = wordService.getAllWordsForUser(loggedUser.getId());
        model.addAttribute("allWordsSize", allWordsForUser == null ? 0 :allWordsForUser.size());

        return "home";
    }

}
