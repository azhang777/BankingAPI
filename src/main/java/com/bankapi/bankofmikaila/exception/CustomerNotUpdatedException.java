package com.bankapi.bankofmikaila.exception;

public class CustomerNotUpdatedException extends RuntimeException{
    public CustomerNotUpdatedException() {
        super();
    }

    public CustomerNotUpdatedException(String message) {
        super(message);
    }
}

