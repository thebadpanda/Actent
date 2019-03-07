package com.softserve.actent.controller;

import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<RegisterUserDto> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return new ResponseEntity<>(registerUserEntityToDto(userService.
                registerUser(registerUserDtoToEntity(registerUserDto))), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserSettingsDto> saveUserSetting(@Valid @RequestBody UserSettingsDto userSettingsDto, @PathVariable Long id) {
        return new ResponseEntity<>(userSettingsEntityToDto(userService.
                saveUserSettings(userSettingsToEntity(userSettingsDto), id)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserSettingsDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userSettingsEntityToDto(userService.getUserById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{email}")
    public ResponseEntity<UserSettingsDto> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userSettingsEntityToDto(userService.getUserByEmail(email)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private User registerUserDtoToEntity(RegisterUserDto registerUserDto) {
        return modelMapper.map(registerUserDto, User.class);
    }

    private User userSettingsToEntity(UserSettingsDto userSettingsDto) {
        return modelMapper.map(userSettingsDto, User.class);
    }

    private UserSettingsDto userSettingsEntityToDto(User entity) {
        return modelMapper.map(entity, UserSettingsDto.class);
    }

    private RegisterUserDto registerUserEntityToDto(User entity) {
        return modelMapper.map(entity, RegisterUserDto.class);
    }
}
