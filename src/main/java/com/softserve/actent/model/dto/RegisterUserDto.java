package com.softserve.actent.model.dto;

import lombok.Data;


@Data
public class RegisterUserDto {
    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
