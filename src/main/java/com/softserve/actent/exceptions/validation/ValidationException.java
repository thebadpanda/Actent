package com.travelstory.exceptions.validation;

import com.travelstory.exceptions.TravelStoryAppException;
import com.travelstory.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h2>General exception for validation</h2>
 * <p>
 * All validation exception codes has next template: <b>1***</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends TravelStoryAppException {
    public ValidationException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
