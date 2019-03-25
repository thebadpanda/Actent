package com.softserve.actent.exceptions.security;

import com.softserve.actent.exceptions.ActentAppException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends ActentAppException {
    public NotAuthorizedException(String  message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
