package com.softserve.actent.exceptions;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>General exception for duplicate input data</h2>
 * <p>
 * Exception has code: <b>1001</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateValueException extends ValidationException {

    public DuplicateValueException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}