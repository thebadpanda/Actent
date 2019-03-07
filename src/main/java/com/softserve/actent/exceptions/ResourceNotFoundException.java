package com.softserve.actent.exceptions;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

<<<<<<< HEAD
/**
 * <h2>General exception for validation</h2>
 * <p>
 * All resource not found exception codes has next template: <b>20**</b>
 * </p>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
=======
@ResponseStatus(HttpStatus.NOT_FOUND)
>>>>>>> 60d91744d8002360822b3818831ca0715d931ad0
public class ResourceNotFoundException extends ActentAppException {

    public ResourceNotFoundException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
