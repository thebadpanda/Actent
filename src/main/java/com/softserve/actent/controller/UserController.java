package com.softserve.actent.controller;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.user.UserRegistrationDto;
import com.softserve.actent.model.dto.user.UserDto;
import com.softserve.actent.model.dto.user.UserSettingsDto;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping(UrlConstants.API_V1)
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
    public IdDto addUser(@Validated @RequestBody UserRegistrationDto userRegistrationDto) {
        User user = userService.add(userRegistrationDtoToEntity(userRegistrationDto));
        return new IdDto(user.getId());
    }

    @PutMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserById(@Validated @RequestBody UserSettingsDto userSettingsDto,
                                  @PathVariable
                                  @NotNull(message = StringConstants.USER_ID_CAN_NOT_BE_NULL)
                                  @Positive(message = StringConstants.USER_ID_SHOULD_BE_GREATER_THAN_ZERO) Long id) {

        User user =(userSettingsToEntity(userSettingsDto));

        return userSettingsEntityToDto(userService.update(user, id));
    }

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserSettingsDto> getUsers(@RequestParam(value = "email", required = false)
                                          @Email(message = StringConstants.USER_EMAIL_NOT_VALID)
                                          @Size(max = NumberConstants.USER_EMAIL_MAX_LENGTH, message = StringConstants.USER_EMAIL_LENGTH_RANGE)
                                                  String email) {
        if (email == null) {
            List<UserSettingsDto> userDtoList = new ArrayList<>();
            for (User user : userService.getAll()) {
                userDtoList.add(userEntityToSettingDto(user));
            }
            return userDtoList;
        }
        List<UserSettingsDto> userDtoList = new ArrayList<>();
        UserSettingsDto userDto = userEntityToSettingDto(userService.getUserByEmail(email));
        userDtoList.add(userDto);
        return userDtoList;
    }

    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable
                               @NotNull(message = StringConstants.USER_ID_CAN_NOT_BE_NULL)
                               @Positive(message = StringConstants.USER_ID_SHOULD_BE_GREATER_THAN_ZERO) Long id) {
        return userSettingsEntityToDto(userService.get(id));
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable
                               @NotNull(message = StringConstants.USER_ID_CAN_NOT_BE_NULL)
                               @Positive(message = StringConstants.USER_ID_SHOULD_BE_GREATER_THAN_ZERO) Long id) {
        userService.delete(id);
    }

    private User userRegistrationDtoToEntity(UserRegistrationDto userRegistrationDto) {
        return modelMapper.map(userRegistrationDto, User.class);
    }

    private User userSettingsToEntity(UserSettingsDto userSettingsDto) {
        return modelMapper.map(userSettingsDto, User.class);
    }

    private UserDto userSettingsEntityToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    private UserSettingsDto userEntityToSettingDto(User user) {
        return modelMapper.map(user, UserSettingsDto.class);
    }

}
