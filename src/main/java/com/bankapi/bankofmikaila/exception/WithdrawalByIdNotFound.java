package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WithdrawalByIdNotFound extends EntityNotFoundException {
    public WithdrawalByIdNotFound() {
    }

    public WithdrawalByIdNotFound(String message) {
        super(message);
    }
}
