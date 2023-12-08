package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepositByIdNotFound extends EntityNotFoundException {

    public DepositByIdNotFound() {
    }

    public DepositByIdNotFound(String message) {
        super(message);
    }
}
