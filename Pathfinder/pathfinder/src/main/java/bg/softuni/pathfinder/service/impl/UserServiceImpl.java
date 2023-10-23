package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exception.UserNotFoundException;
import bg.softuni.pathfinder.model.dto.view.UserProfileViewModel;
import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getLoggedUser() {
        return userRepository.findByUsername(loggedUser.getUsername())
                .orElse(null);
    }

    @Override
    public UserProfileViewModel getUserProfile() {
        String username = loggedUser.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username +  " was not found!"));

        return modelMapper.map(user, UserProfileViewModel.class);
    }
}
