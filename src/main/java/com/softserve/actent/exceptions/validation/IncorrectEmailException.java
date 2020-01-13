package com.softserve.actent.exceptions.validation;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectEmailException extends IncorrectInputDataException {
    public IncorrectEmailException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
