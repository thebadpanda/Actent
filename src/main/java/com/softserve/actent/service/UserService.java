package com.softserve.actent.service;

import com.softserve.actent.model.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    User saveUserSettings(User user, Long id);

    List<User> getUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void deleteUserById(Long id);
}
