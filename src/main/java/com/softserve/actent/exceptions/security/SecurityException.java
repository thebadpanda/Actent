package com.softserve.actent.exceptions.security;

import com.softserve.actent.exceptions.ActentAppException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>General exception for security</h2>
 * <p>
 * All security exception codes has next template <b>7***</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class SecurityException extends ActentAppException {
    public SecurityException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
