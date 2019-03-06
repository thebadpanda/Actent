package com.softserve.actent.exceptions;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ActentAppException {

    public ResourceNotFoundException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
