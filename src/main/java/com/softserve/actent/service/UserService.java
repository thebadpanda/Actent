package com.softserve.actent.service;

import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.entity.User;

import java.util.List;

public interface UserService {

    boolean registerUser(RegisterUserDto registerUserDto);

    boolean saveUserSettings(UserSettingsDto userSettingsDto);

    List<User> getUsers();

    User getUserById(Long id);

    void deleteUserById(Long id);
}
