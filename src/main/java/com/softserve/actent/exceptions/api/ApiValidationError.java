package com.softserve.actent.exceptions.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedMessage;
    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
