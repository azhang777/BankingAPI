package com.bankapi.bankofmikaila.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.AccountNotFoundException;
import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountsNotFoundException extends NoSuchElementException {
    public AccountsNotFoundException() {

    }

    public AccountsNotFoundException(String message) {
        super(message);
    }

    public AccountsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
