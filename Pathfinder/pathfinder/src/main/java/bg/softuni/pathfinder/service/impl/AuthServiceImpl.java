package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exception.LoginCredentialsException;
import bg.softuni.pathfinder.model.dto.binding.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.AuthService;
import bg.softuni.pathfinder.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = modelMapper.map(userRegisterBindingModel, User.class);
        userRepository.save(user);
    }

    @Override
    public void login(UserLoginBindingModel userLoginBindingModel) throws LoginCredentialsException {
        String username = userLoginBindingModel.getUsername();

        User user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new LoginCredentialsException("User with username: [" + username + "] is not present"));

        boolean passwordMatch = passwordEncoder.matches(userLoginBindingModel.getPassword(),
                user.getPassword());

        if (!passwordMatch){
            throw new LoginCredentialsException("User entered incorrect password");
        }

        loggedUser.setUsername(user.getUsername());
        loggedUser.setLogged(true);
    }

    @Override
    public void logout() {
        loggedUser.reset();
    }
    private void setLoggedUser(User byUsername) {
        loggedUser.setEmail(byUsername.getEmail());
        loggedUser.setUsername(byUsername.getUsername());
        loggedUser.setFullName(byUsername.getFullName());
        loggedUser.setLogged(true);
    }
}
