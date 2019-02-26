package com.softserve.actent.model.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateUserDto {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String login;

}
