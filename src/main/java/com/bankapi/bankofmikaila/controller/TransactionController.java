package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.response.TransactionResponse;
import com.bankapi.bankofmikaila.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
