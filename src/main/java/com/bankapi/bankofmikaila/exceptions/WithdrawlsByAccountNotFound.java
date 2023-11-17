package com.bankapi.bankofmikaila.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WithdrawlsByAccountNotFound extends EntityNotFoundException {


    public WithdrawlsByAccountNotFound(){

    }

    public WithdrawlsByAccountNotFound(String message){
        super(message);
    }


}














