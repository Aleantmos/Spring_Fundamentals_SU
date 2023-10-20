package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.entities.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User getLoggedUser();
}
