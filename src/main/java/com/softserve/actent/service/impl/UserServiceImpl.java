package com.softserve.actent.service.impl;

import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectEmailException;
import com.softserve.actent.model.dto.UserSettingsDto;
import com.softserve.actent.model.dto.RegisterUserDto;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.UserService;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, IModelMapperConverter<User, UserSettingsDto> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    User user;

    @Transactional
    @Override
    public RegisterUserDto registerUser(RegisterUserDto registerUserDto) {

        if (!userRepository.existsById(registerUserDto.getId())) {
            userRepository.save(registerUserDtoToEntity(registerUserDto));
            return registerUserDto;

        } else {
            System.out.println("There is a user with such id");
            throw new IncorrectEmailException("This email already used!", ExceptionCode.INCORRECT_EMAIL);
        }
    }

    @Transactional
    @Override
    public UserSettingsDto saveUserSettings(UserSettingsDto userSettingsDto) {

        if (userRepository.existsById(userSettingsDto.getId())) {
            userRepository.save(convertToEntity(userSettingsDto));
            return userSettingsDto;
        } else {
            System.out.println("There is a user with such id");
            // TODO: throw exception
            return null;
        }
    }


    private User registerUserDtoToEntity(RegisterUserDto registerUserDto) {
        User user = modelMapper.map(registerUserDto, User.class);
        return user;
    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public UserSettingsDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        UserSettingsDto dto = modelMapper.map(user, UserSettingsDto.class);
        return dto;
    }

    @Override
    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public User convertToEntity(UserSettingsDto dto) {
        User user = modelMapper.map(dto, User.class);
        return user;
    }

    @Override
    public UserSettingsDto convertToDto(User entity) {
        UserSettingsDto userSettingsDto = modelMapper.map(entity, UserSettingsDto.class);
        return userSettingsDto;
    }
}
