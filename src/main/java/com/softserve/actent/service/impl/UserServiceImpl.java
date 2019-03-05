package com.softserve.actent.service.impl;

import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    User user;

    @Transactional
    @Override
    public boolean registerUser(@RequestBody RegisterUserDto registerUserDto) {
        boolean isSuccess;
        if (!userRepository.existsById(registerUserDto.getId())) {
            userRepository.save(userEntityToDto(registerUserDto));
            isSuccess = true;
        } else {
            System.out.println("There is a user with such id");
            isSuccess = false;
        }
        return isSuccess;
    }

    @Transactional
    @Override
    public boolean saveUserSettings(@RequestBody UserSettingsDto userSettingsDto) {
        boolean isSuccess;
        if (userRepository.existsById(userSettingsDto.getId())) {
            userRepository.save(userSettingsToDto(userSettingsDto));
            isSuccess = true;
        } else {
            isSuccess = false;
        }
        return isSuccess;
    }

    private User userEntityToDto(RegisterUserDto registerUserDto) {
        User user = new User();
        user.setEmail(registerUserDto.getEmail());
        user.setLogin(registerUserDto.getLogin());
        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setPassword(registerUserDto.getPassword());
        return user;
    }

    private User userSettingsToDto(UserSettingsDto userSettingsDto){
        User user = new User();
        user.setEmail(userSettingsDto.getEmail());
        user.setLogin(userSettingsDto.getLogin());
        user.setFirstName(userSettingsDto.getFirstName());
        user.setLastName(userSettingsDto.getLastName());
        user.setBirthDate(userSettingsDto.getBirthDate());
        user.setAvatar(userSettingsDto.getAvatar());
        user.setBio(userSettingsDto.getBio());
        user.setSex(userSettingsDto.getSex());
        user.setLocation(userSettingsDto.getLocation());
        user.setRole(userSettingsDto.getRole());
        return user;
    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }
}
