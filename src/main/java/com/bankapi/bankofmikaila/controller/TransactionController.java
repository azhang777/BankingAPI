package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.response.TransactionResponse;
import com.bankapi.bankofmikaila.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class TransactionController {

    @Autowired
    private TransactionResponse transactionResponse;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("transactions")
    public ResponseEntity<?> getAllTransactions(){

        return transactionResponse.getAllTransactions();

    }


}
