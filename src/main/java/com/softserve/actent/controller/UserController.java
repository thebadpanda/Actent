package com.softserve.actent.controller;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectEmailException;
import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterUserDto> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        if (userService.getUserByEmail(registerUserDto.getEmail()) != null) {
            throw new IncorrectEmailException("Email already used", ExceptionCode.INCORRECT_EMAIL);
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(userService.registerUser(registerUserDto), HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserSettingsDto> saveUserSetting(@RequestBody UserSettingsDto userSettingsDto) {
        return new ResponseEntity<>(userService.saveUserSettings(userSettingsDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserSettingsDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}










