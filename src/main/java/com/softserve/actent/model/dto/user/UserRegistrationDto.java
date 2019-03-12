package com.softserve.actent.model.dto.user;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class UserRegistrationDto {

    @NotBlank(message = StringConstants.EMPTY_USER_LOGIN)
    @Size(max = NumberConstants.USER_LOGIN_MAX_LENGTH, min = NumberConstants.USER_LOGIN_MIN_LENGTH, message = StringConstants.USER_LOGIN_LENGTH_RANGE)
    private String login;

    @NotBlank(message = StringConstants.EMPTY_USER_EMAIL)
    @Size(max = NumberConstants.USER_EMAIL_MAX_LENGTH, message = StringConstants.USER_EMAIL_LENGTH_RANGE)
    @Email(message = StringConstants.EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = StringConstants.EMPTY_USER_FIRST_NAME)
    @Size(max = NumberConstants.USER_FIRST_NAME_MAX_LENGTH, min = NumberConstants.USER_FIRST_NAME_MIN_LENGTH, message = StringConstants.USER_FIRST_NAME_LENGTH_RANGE)
    private String firstName;

    @NotBlank(message = StringConstants.EMPTY_USER_LAST_NAME)
    @Size(max = NumberConstants.USER_LAST_NAME_MAX_LENGTH, min = NumberConstants.USER_LAST_NAME_MIN_LENGTH, message = StringConstants.USER_LAST_NAME_LENGTH_RANGE)
    private String lastName;

    @NotBlank(message = StringConstants.EMPTY_USER_PASSWORD)
    @Size(max = NumberConstants.USER_PASSWORD_MAX_LENGTH, min = NumberConstants.USER_PASSWORD_MIN_LENGTH, message = StringConstants.USER_PASSWORD_LENGTH_RANGE)
    private String password;
}
