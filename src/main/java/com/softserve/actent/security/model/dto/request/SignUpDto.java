package com.softserve.actent.security.model.dto.request;

import lombok.Data;

// TODO: provide validation
@Data
public class SignUpDto {

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String password;
}
