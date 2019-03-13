package com.softserve.actent.exceptions.validation;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MessageValidationException extends IncorrectInputDataException {
    public MessageValidationException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
