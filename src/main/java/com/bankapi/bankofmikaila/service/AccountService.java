package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts() {
        return null;
    }

    public Account getAccountById(Long accountId) {
        return null;
    }

    public Account getAllCustomerAccounts(Long customerId) {
        return null;
    }

    public Account createAnAccount(Long customerId, Account newAccount) {
        return null;
    }

    public Account updateAccount(Long accountId, Account updatedAccount) {
        return null;
    }

    public Account deleteAccount(Long accountId) {
        return null;
    }
}
