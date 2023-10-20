package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.dto.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.UserRegisterBindingModel;
import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.AuthService;
import bg.softuni.pathfinder.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User toRegister = modelMapper.map(userRegisterBindingModel, User.class);
        toRegister.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(toRegister);
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        User byUsername = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (byUsername == null) {
            throw new IllegalArgumentException("User not found exception");
        }

        boolean passwordChecker = passwordEncoder
                .matches(userLoginBindingModel.getPassword(), byUsername.getPassword());

        if (!passwordChecker) {
            throw new IllegalArgumentException("Incorrect password");
        }

        setLoggedUser(byUsername);

        return true;
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
