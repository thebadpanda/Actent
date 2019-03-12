package com.softserve.actent.model.dto;

import com.softserve.actent.constant.NumberConstants;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotNull(message = StringConstants.EMPTY_USER_ID)
    @Positive(message = StringConstants.USER_ID_SHOULD_BE_GREATER_THAN_ZERO)
    private Long id;

    @NotBlank(message = StringConstants.EMPTY_USER_LOGIN)
    @Length(max = NumberConstants.USER_LOGIN_MAX_LENGTH, min = NumberConstants.USER_LOGIN_MIN_LENGTH, message = StringConstants.USER_LOGIN_LENGTH_RANGE)
    private String login;

    @NotBlank(message = StringConstants.EMPTY_USER_EMAIL)
    @Length(max = NumberConstants.USER_EMAIL_MAX_LENGTH, message = StringConstants.USER_EMAIL_LENGTH_RANGE)
    @Email(message = StringConstants.EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = StringConstants.EMPTY_USER_FIRST_NAME)
    @Length(max = NumberConstants.USER_FIRST_NAME_MAX_LENGTH, min = NumberConstants.USER_FIRST_NAME_MIN_LENGTH, message = StringConstants.USER_FIRST_NAME_LENGTH_RANGE)
    private String firstName;

    @NotBlank(message = StringConstants.EMPTY_USER_LAST_NAME)
    @Length(max = NumberConstants.USER_LAST_NAME_MAX_LENGTH, min = NumberConstants.USER_LAST_NAME_MIN_LENGTH, message = StringConstants.USER_LAST_NAME_LENGTH_RANGE)
    private String lastName;

    @NotBlank(message = StringConstants.EMPTY_USER_PASSWORD)
    @Length(max = NumberConstants.USER_PASSWORD_MAX_LENGTH, min = NumberConstants.USER_PASSWORD_MIN_LENGTH, message = StringConstants.USER_PASSWORD_LENGTH_RANGE)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = StringConstants.BIRTHDAY_CANNOT_BE_FUTURE_DATE)
    private LocalDate birthDate;

    private Image avatar;

    private Location location;

    @Length(max = NumberConstants.USER_BIO_MAX_LENGTH, message = StringConstants.BIO_TOO_LONG)
    private String bio;

    @Length(max = NumberConstants.USER_SEX_MAX_LENGTH, message = StringConstants.SEX_TOO_LONG)
    private String sex;

    private Role role;

}
