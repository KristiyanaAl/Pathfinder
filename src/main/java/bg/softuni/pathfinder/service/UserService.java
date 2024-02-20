package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel serviceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserServiceModel findById(Long id);

    boolean findByUsername(String username);
}
