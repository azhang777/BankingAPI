package com.bankapi.bankofmikaila.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class BillsByCustomerIdNotFoundException extends EntityNotFoundException {
    public BillsByCustomerIdNotFoundException() {
    }

    public BillsByCustomerIdNotFoundException(String message) {
        super(message);
    }
}
