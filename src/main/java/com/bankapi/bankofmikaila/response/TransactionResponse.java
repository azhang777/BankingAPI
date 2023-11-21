package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.service.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponse {

    @Autowired
    private TransactionFactory transactionService;



}
