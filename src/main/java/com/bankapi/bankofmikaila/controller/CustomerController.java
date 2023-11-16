package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.response.AccountResponse;
import com.bankapi.bankofmikaila.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    //single responsibility principle - each controller is responsible for a specific set of related functionalities.
    //that is why we separate account and customer controller

    @Autowired
    private CustomerResponse customerResponse;

    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<?> getAllCustomerAccounts(@PathVariable Long customerId) {
        return customerResponse.getAllCustomerAccounts(customerId);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account newAccount) {
        return customerResponse.createAccount(customerId, newAccount);
    }
}
