package com.bankapi.bankofmikaila.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidTypeException extends InvalidParameterException {
    public InvalidTypeException() {

    }

    public InvalidTypeException(String message) {
        super(message);
    }
}
