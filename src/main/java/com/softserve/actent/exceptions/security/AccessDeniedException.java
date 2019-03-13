package com.softserve.actent.exceptions.security;

import com.softserve.actent.exceptions.ActentAppException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessDeniedException extends ActentAppException {
    public AccessDeniedException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
