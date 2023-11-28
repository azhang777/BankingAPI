package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
public class DepositController {


    @Autowired
    TransactionResponse transactionResponse;

    @GetMapping("accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDeposits(@PathVariable Long accountId) {

        return transactionResponse.getAllDeposits(accountId);

    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDeposit(@PathVariable Long depositId) {

        return transactionResponse.getDeposit(depositId);

    }

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody Transaction deposit, @PathVariable Long accountId) {

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDepositURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{depositId}").buildAndExpand(deposit.getId()).toUri();
        responseHeaders.setLocation(newDepositURI);

        return transactionResponse.createDeposit(deposit, accountId);

    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@RequestBody Transaction deposit, @PathVariable Long depositId) {

        return transactionResponse.updateDeposit(deposit, depositId);

    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {

        return transactionResponse.deleteDeposit(depositId);

    }

}

