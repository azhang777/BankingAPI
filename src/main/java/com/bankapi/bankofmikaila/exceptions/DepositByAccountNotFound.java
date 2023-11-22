package com.bankapi.bankofmikaila.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepositByAccountNotFound extends EntityNotFoundException {

    public DepositByAccountNotFound() {
    }

    public DepositByAccountNotFound(String message) {
        super(message);
    }
}
