package com.bankapi.bankofmikaila.controller;


import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.response.DepositResponse;
import com.bankapi.bankofmikaila.service.DepositService;
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
    @Autowired
    DepositService depositService;

    @GetMapping("accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDeposits(){

        return depositResponse.getAllDeposits();

    }

//    @PostMapping("/accounts/{accountId}/deposits")
//    public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit, @PathVariable Long accountId){
//
////        depositResponse.createDeposit(deposit);
//
//        depositService.createDeposit(deposit);
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newDepositURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{depositId}").buildAndExpand(deposit.getId()).toUri();
//        responseHeaders.setLocation(newDepositURI);
//
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }

    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long depositId){

        depositService.updateDeposit(deposit, depositId);

        return new ResponseEntity<>("Accepted deposit modification", HttpStatus.OK);
    }

}
