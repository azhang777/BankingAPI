package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.response.AccountResponse;
import com.bankapi.bankofmikaila.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountResponse accountResponse;
    @Autowired
    private CustomerResponse customerResponse;

    @GetMapping("")
    public ResponseEntity<?> getAllAccounts() {
        return accountResponse.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        return accountResponse.getAccountById(accountId);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount) {
        return accountResponse.updateAccount(accountId, updatedAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        return accountResponse.deleteAccount(accountId);
    }

    @GetMapping("/{accountId}/customer")
    public ResponseEntity<?> getCustomerByAccountId (@PathVariable Long accountId){
        return customerResponse.getCustomerByAccountId(accountId);
    }
}
