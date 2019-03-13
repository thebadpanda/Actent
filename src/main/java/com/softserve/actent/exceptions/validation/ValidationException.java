package com.softserve.actent.exceptions.validation;

import com.softserve.actent.exceptions.ActentAppException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends ActentAppException {
    public ValidationException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
