package com.hotel.impl.admin.user;

import com.hotel.impl.admin.room.Room;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList(String searchText, List<String> searchParametersList) {
        return userRepository.getUserList(searchText, searchParametersList);
    }

    public boolean setRoom(User user) {
        return userRepository.setUser(user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public boolean updateRoom(User user) {
        return userRepository.updateUser(user);
    }

    public User getUser(int id) {
        return userRepository.getUser(id);
    }

    public Optional<User> authUser(String email) {
        return userRepository.authUser(email);
    }
}
