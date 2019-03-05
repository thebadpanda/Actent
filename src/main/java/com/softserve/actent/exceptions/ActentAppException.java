package com.softserve.actent.exceptions;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ToString
public class ActentAppException extends RuntimeException {
    private String message;
    private ExceptionCode exceptionCode;
}
