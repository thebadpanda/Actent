package com.softserve.actent.verification.service;

import com.softserve.actent.model.entity.User;

public interface SendEmail {
    boolean sendSimpleEmail(String email, User user);
}
