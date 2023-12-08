package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TransactionStatusNotValidException extends UnsupportedOperationException{


    public TransactionStatusNotValidException() {
    }

    public TransactionStatusNotValidException(String message) {
        super(message);
    }
}
