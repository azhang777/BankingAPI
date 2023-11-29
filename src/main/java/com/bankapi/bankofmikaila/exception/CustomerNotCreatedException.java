package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CustomerNotCreatedException extends RuntimeException{
    public CustomerNotCreatedException() {
        super();
    }

    public CustomerNotCreatedException(String message) {
        super(message);
    }
}
