package com.hotel.impl.admin.user;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList(String searchText, List<String> searchParametersList) {
        return userRepository.getUserList(searchText, searchParametersList);
    }

    public boolean setUser(User user) {
        return userRepository.setUser(user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public User getUser(int id) {
        return userRepository.getUser(id);
    }

    public Optional<User> authUser(String email) {
        return userRepository.authUser(email);
    }
}
