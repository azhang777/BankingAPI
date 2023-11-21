package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponse {

    @Autowired
    private TransactionService transactionService;



}
