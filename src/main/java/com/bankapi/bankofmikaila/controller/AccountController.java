package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.response.AccountResponse;
import com.bankapi.bankofmikaila.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountResponse accountResponse;
    @Autowired
    private CustomerResponse customerResponse;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<?> getAllAccounts() {
        return accountResponse.getAllAccounts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        return accountResponse.getAccountById(accountId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount) {
        return accountResponse.updateAccount(accountId, updatedAccount);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        return accountResponse.deleteAccount(accountId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/{accountId}/customer")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId) {
        return customerResponse.getCustomerByAccountId(accountId);
    }
}
