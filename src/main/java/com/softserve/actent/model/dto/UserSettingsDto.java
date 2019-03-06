package com.softserve.actent.model.dto;

import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.model.entity.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserSettingsDto {

    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthDate;
//    private String locationAddress;
    private Image avatar;
    private Location location;
    private String bio;
    private String sex;
    private Role role;


}
