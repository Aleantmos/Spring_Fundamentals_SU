package bg.softuni.pathfinder.controller;

import bg.softuni.pathfinder.model.dto.binding.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.pathfinder.model.dto.view.UserProfileViewModel;
import bg.softuni.pathfinder.service.AuthService;
import bg.softuni.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final AuthService authService;
    private final UserService userService;

    public UsersController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginBindingModel userLoginBindingModel) {

        boolean isLogged = authService.login(userLoginBindingModel);

        if (isLogged) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel) {
        this.authService.register(userRegisterBindingModel);

        return new ModelAndView("redirect:login");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        this.authService.logout();
        return new ModelAndView("index");
    }

    @GetMapping("/details")
    public ModelAndView profile() {
        UserProfileViewModel userProfileViewModel = userService.getUserProfile();

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("userProfileViewModel", userProfileViewModel);

        return modelAndView;
    }

}
