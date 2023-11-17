package com.bankapi.bankofmikaila.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountsNotFoundException extends EntityNotFoundException {
    public AccountsNotFoundException() {

    }

    public AccountsNotFoundException(String message) {
        super(message);
    }
}
