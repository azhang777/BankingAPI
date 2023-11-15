package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.service.AccountService;
import com.bankapi.bankofmikaila.success.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountResponse accountResponse;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllCustomerAccounts(@PathVariable Long customerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> createAnAccount(@PathVariable Long customerId, @RequestBody Account newAccount) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
