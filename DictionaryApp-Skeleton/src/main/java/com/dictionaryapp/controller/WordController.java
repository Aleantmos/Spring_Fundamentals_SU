package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {

    private final LoggedUser loggedUser;
    private final WordService wordService;

    @Autowired
    public WordController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/add-word")
    public String addWord() {
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }
        return "word-add";
    }

    @PostMapping("/add-word")
    public String addWord(@Valid AddWordDTO addWordDTO,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addWordDTO", addWordDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addWordDTO", result);

            return "redirect:/words/add-word";
        }
        addWordDTO.setAddedById(loggedUser.getId());

        this.wordService.addWords(addWordDTO);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable Long id) {
        wordService.removeWordById(id);
        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {
        wordService.removeAll();
        return "home";
    }

    @ModelAttribute("addWordDTO")
    public AddWordDTO addWordDTO() {
        return new AddWordDTO();
    }
}
