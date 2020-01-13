package com.softserve.actent.exceptions;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateValueException extends ValidationException {

    public DuplicateValueException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}