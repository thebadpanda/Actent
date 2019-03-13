package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.security.AccessDeniedException;
import com.softserve.actent.exceptions.validation.IncorrectEmailException;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.ImageService;
import com.softserve.actent.service.LocationService;
import com.softserve.actent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LocationService locationService;
    private final ImageService imageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           LocationService locationService,
                           ImageService imageService) {

        this.userRepository = userRepository;
        this.locationService = locationService;
        this.imageService = imageService;
    }

    @Transactional
    @Override
    public User add(User user) {
//        if (!userRepository.existsByEmail(user.getEmail())) {
            Optional<User> optionalUser = userRepository.findById(user.getId());
            return optionalUser.orElseGet(()-> userRepository.save(user));
//        } else {
//            throw new IncorrectEmailException(ExceptionMessages.EMAIL_ALREADY_USED, ExceptionCode.INCORRECT_EMAIL);
//        }
    }

    @Transactional
    @Override
    public User update(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            user.setLocation(locationService.get(user.getLocation().getId()));
            user.setAvatar(imageService.get(user.getAvatar().getId()));
            return userRepository.save(user);
        } else {
            throw new AccessDeniedException(ExceptionMessages.USER_NOT_REGISTRED, ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.USER_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException(ExceptionMessages.USER_BY_THIS_EMAIL_IS_NOT_FOUND, ExceptionCode.NOT_FOUND));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.USER_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }
    }
}
