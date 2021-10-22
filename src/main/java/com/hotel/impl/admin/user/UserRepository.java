package com.hotel.impl.admin.user;

import com.hotel.impl.admin.room.Room;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getUserList(String searchText, List<String> searchParametersList);

    boolean setUser(User user);

    User getUser(int id);

    boolean deleteUser(int id);

    boolean updateUser(User user);

    Optional<User> authUser(String email);
}
