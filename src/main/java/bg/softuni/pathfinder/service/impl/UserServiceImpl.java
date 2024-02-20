package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.entity.enums.Level;
import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
        user.setLevel(Level.BEGINNER);

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {


        return userRepository.findByUsernameAndPassword(username,password)
                .map(u->modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public UserServiceModel findById(Long id) {

        return userRepository.findById(id)
                .map(u -> modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
//        return modelMapper
//                .map(userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
