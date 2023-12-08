package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundByIdException extends RuntimeException{
    public CustomerNotFoundByIdException() {
        super();
    }

    public CustomerNotFoundByIdException(String message) {
        super(message);
    }
}


