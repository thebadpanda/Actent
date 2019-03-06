package com.softserve.actent.service;

import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.entity.User;

import java.util.List;

public interface UserService {

    RegisterUserDto registerUser(RegisterUserDto registerUserDto);

    UserSettingsDto saveUserSettings(UserSettingsDto userSettingsDto);

    List<User> getUsers();

    UserSettingsDto getUserById(Long id);

    User getUserByEmail(String email);

    void deleteUserById(Long id);
}
