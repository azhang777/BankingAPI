package com.bankapi.bankofmikaila.exception;

public class CustomerHasAccountException extends RuntimeException {
    public CustomerHasAccountException(String message) {
        super(message);
    }
}
