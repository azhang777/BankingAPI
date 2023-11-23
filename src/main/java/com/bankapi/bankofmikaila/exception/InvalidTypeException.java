package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseStatus;


//this may not be needed
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidTypeException extends HttpMessageNotReadableException {
    public InvalidTypeException(String msg) {
        super(msg);
    }
}
