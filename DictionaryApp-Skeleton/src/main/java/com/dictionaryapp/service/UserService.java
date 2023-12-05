package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.LoginDTO;
import com.dictionaryapp.model.dto.RegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepo;
import com.dictionaryapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper,
                       UserRepo userRepo,
                       LoggedUser loggedUser) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.loggedUser = loggedUser;
    }

    public boolean checkPasswordEquality(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public void register(RegisterDTO registerDTO) {
        User toRegister = modelMapper.map(registerDTO, User.class);
        toRegister.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepo.save(toRegister);
    }

    public User checkIfUserIsFoundInDB(LoginDTO loginDTO) {

        User userByUsername = userRepo.findUserByUsername(loginDTO.getUsername())
                .orElse(null);

        if (userByUsername != null) {
            String encoded = userByUsername.getPassword();
            String raw = loginDTO.getPassword();

            if (passwordEncoder.matches(raw, encoded)) {
                return userByUsername;
            }
        }

        return userByUsername;
    }

    public void loginUser(User userToLogin) {
        loggedUser.loginUser(userToLogin.getId(), userToLogin.getUsername());
    }

    public User findUserById(Long addedById) {
        return userRepo.findUserById(addedById);
    }
}
