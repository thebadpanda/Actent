package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
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
