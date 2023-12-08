package com.bankapi.bankofmikaila.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotUpdatedException extends RuntimeException{
    public CustomerNotUpdatedException() {
        super();
    }

    public CustomerNotUpdatedException(String message) {
        super(message);
    }
}

