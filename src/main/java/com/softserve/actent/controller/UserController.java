package com.softserve.actent.controller;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.ValidationException;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.dto.UserDto;
import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addUser(@Validated @RequestBody RegisterUserDto registerUserDto, BindingResult bindingResult) {

        // TODO: change to better practise
        if (bindingResult.hasErrors()){
            List<String> errorList = new ArrayList<>();
            for (int error = 0; error < bindingResult.getErrorCount(); error++){
                String errorMessage = bindingResult.getAllErrors().get(error).getDefaultMessage();
                errorList.add(errorMessage);
            }
            System.out.println(errorList);
        }
        User user = userService.add(registerUserDtoToEntity(registerUserDto));
        return new IdDto(user.getId());
    }

    @PutMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public UserDto updateUserById(@RequestBody UserSettingsDto userSettingsDto, @PathVariable Long id) {
        checkUserIdNotNull(id);
        User user = userService.update(userSettingsToEntity(userSettingsDto), id);
        UserDto userDto = userSettingsEntityToDto(user);
        return userDto;
    }

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers(@RequestParam(value = "email", required = false) String email) {
        if (email == null) {
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user : userService.getAll()) {
                userDtoList.add(userSettingsEntityToDto(user));
            }
            return userDtoList;
        }
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = userSettingsEntityToDto(userService.getUserByEmail(email));
        userDtoList.add(userDto);
        return userDtoList;
    }

    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id) {
        checkUserIdNotNull(id);
        return userSettingsEntityToDto(userService.get(id));
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        checkUserIdNotNull(id);
        userService.delete(id);
    }

    private User registerUserDtoToEntity(RegisterUserDto registerUserDto) {
        return modelMapper.map(registerUserDto, User.class);
    }

    private User userSettingsToEntity(UserSettingsDto userSettingsDto) {
        return modelMapper.map(userSettingsDto, User.class);
    }

    private UserDto userSettingsEntityToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    private UserDto registerUserEntityToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    private void checkUserIdNotNull(Long id){
        if (id == null){
            throw new ValidationException("User id not defined", ExceptionCode.VALIDATION_FAILED);
        }
    }
}
