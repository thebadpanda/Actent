package com.softserve.actent.service;

import com.softserve.actent.model.entity.User;

public interface UserService extends BaseCrudService<User>{

    User getUserByEmail(String email);

    User getUserByLogin(String login);

    User registrationUpdate(User user, Long id);
}
