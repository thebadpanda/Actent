package com.softserve.actent.model.dto;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.model.entity.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class UserSettingsDto {

    @NotBlank(message = StringConstants.EMPTY_USER_LOGIN)
    @Length(max = 20, min = 5, message = StringConstants.USER_LOGIN_LENGTH_RANGE)
    private String login;

    @NotBlank(message = StringConstants.EMPTY_USER_EMAIL)
    @Length(max = 30, message = StringConstants.USER_EMAIL_LENGTH_RANGE)
    @Email(message = StringConstants.EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = StringConstants.EMPTY_USER_FIRST_NAME)
    @Length(max = 20, min = 2, message = StringConstants.USER_FIRST_NAME_LENGTH_RANGE)
    private String firstName;

    @NotBlank(message = StringConstants.EMPTY_USER_LAST_NAME)
    @Length(max = 20, min = 2, message = StringConstants.USER_LAST_NAME_LENGTH_RANGE)
    private String lastName;

    @NotBlank(message = StringConstants.EMPTY_USER_PASSWORD)
    @Length(max = 30, min = 6, message = StringConstants.USER_PASSWORD_LENGTH_RANGE)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = StringConstants.BIRTHDAY_CANNOT_BE_FUTURE_DATE)
    private LocalDate birthDate;

    private Image avatar;

    private UserLocationDto location;

    @Length(max = 500, message = StringConstants.BIO_TOO_LONG)
    private String bio;

    @Length(max = 20, message = StringConstants.SEX_TOO_LONG)
    private String sex;

    private Role role;

}
