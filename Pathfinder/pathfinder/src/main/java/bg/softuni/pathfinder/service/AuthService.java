package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.binding.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.binding.UserRegisterBindingModel;

public interface AuthService {

    void register(UserRegisterBindingModel userRegisterBindingModel);

    void login(UserLoginBindingModel userLoginBindingModel);

    void logout();
}
