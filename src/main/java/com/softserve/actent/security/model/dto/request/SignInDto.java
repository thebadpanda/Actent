package com.softserve.actent.security.model.dto.request;

import lombok.Data;

// TODO: provide validation
@Data
public class SignInDto {

    private String usernameOrEmail;

    private String password;
}
