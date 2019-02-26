package com.softserve.actent.service;

import com.softserve.actent.model.entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    void deleteUserById(Long id);
}
