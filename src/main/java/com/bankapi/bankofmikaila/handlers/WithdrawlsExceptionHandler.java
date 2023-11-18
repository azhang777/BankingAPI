package com.bankapi.bankofmikaila.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class WithdrawlsExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;



}
