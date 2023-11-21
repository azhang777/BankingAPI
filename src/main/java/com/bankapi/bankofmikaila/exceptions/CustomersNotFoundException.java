package com.bankapi.bankofmikaila.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomersNotFoundException extends EntityNotFoundException {

    public CustomersNotFoundException(String message) {
        super (message);
    }

}
