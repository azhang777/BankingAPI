package com.bankapi.bankofmikaila.exception;

public class CustomerNotFoundByIdException extends RuntimeException{
    public CustomerNotFoundByIdException() {
        super();
    }

    public CustomerNotFoundByIdException(String message) {
        super(message);
    }
}


