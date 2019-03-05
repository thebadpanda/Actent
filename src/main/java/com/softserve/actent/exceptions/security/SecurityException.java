package com.travelstory.exceptions.security;

import com.travelstory.exceptions.TravelStoryAppException;
import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>General exception for security</h2>
 * <p>
 * All security exception codes has next template <b>7***</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class SecurityException extends TravelStoryAppException {
    public SecurityException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
