


package com.bankapi.bankofmikaila.controller;


import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.response.DepositResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class DepositController {

    @Autowired
    DepositResponse depositResponse;


    @GetMapping("accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDeposits(@PathVariable Long accountId){

        return depositResponse.getAllDeposits(accountId);

    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDeposit(@PathVariable Long depositId){

        return depositResponse.getDeposit(depositId);

    }

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit, @PathVariable Long accountId){

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDepositURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{depositId}").buildAndExpand(deposit.getId()).toUri();
        responseHeaders.setLocation(newDepositURI);

        return depositResponse.createDeposit(deposit, accountId);

    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long depositId){

        return depositResponse.updateDeposit(deposit, depositId);

    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId){

        return depositResponse.deleteDeposit(depositId);

    }

}

