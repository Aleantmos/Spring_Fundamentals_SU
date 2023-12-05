package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.LoginDTO;
import com.dictionaryapp.model.dto.RegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    @Autowired
    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegisterDTO registerDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.checkPasswordEquality(
                registerDTO.getPassword(),
                registerDTO.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute(
                            "registerDTO", registerDTO)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:register";
        }

        userService.register(registerDTO);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        if (!model.containsAttribute("notFound")) {
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid LoginDTO loginDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:login";
        }

        User userToLogin = userService.checkIfUserIsFoundInDB(loginDTO);

        if (userToLogin == null) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        userService.loginUser(userToLogin);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String loggedOut() {
        loggedUser.logout();
        return "index";
    }
    @ModelAttribute(name = "registerDTO")
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute(name = "loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }
}
