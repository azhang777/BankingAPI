package com.bankapi.bankofmikaila.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SingleAccountNotFoundException extends NoSuchElementException {

    public SingleAccountNotFoundException() {

    }

    public SingleAccountNotFoundException(String message) {
        super(message);
    }

    public SingleAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
