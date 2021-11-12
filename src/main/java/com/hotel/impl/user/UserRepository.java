package com.hotel.impl.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getUserList(String searchText, List<String> searchParametersList);

    boolean setUser(User user);

    Optional<User> getUser(int id);

    boolean deleteUser(int id);

    boolean updateUser(User user);

    Optional<User> authUser(String email);
}
