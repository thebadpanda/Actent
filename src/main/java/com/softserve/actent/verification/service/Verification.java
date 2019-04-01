package com.softserve.actent.verification.service;

public interface Verification {

    String createUuidWithSalt();

    boolean confirmUser(String login, String uuid);
}
