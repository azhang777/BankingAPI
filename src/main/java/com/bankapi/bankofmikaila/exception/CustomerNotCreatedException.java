package com.bankapi.bankofmikaila.exception;

public class CustomerNotCreatedException extends RuntimeException{
    public CustomerNotCreatedException() {
        super();
    }

    public CustomerNotCreatedException(String message) {
        super(message);
    }
}
