package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
public class CustomerController {
    //single responsibility principle - each controller is responsible for a specific set of related functionalities.
    //that is why we separate account and customer controller

    @Autowired
    private AccountResponse accountResponse;

    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<?> getAllCustomerAccounts(@PathVariable Long customerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/{customerId}/accounts")
//    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account newAccount) {
//        return accountResponse.createAccount(customerId, newAccount);
//    }
}
