package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.AccountType;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("ERROR ಠ_ಠ ERROR"));
    }

    public Account updateAccount(Long accountId, Account updatedAccount) {
        Account existingAccount = getAccountById(accountId);
        if (updatedAccount.getType().equals(AccountType.SAVINGS) || updatedAccount.getType().equals(AccountType.CHECKING) || updatedAccount.getType().equals(AccountType.CREDIT)) {
            existingAccount.setType(updatedAccount.getType());
        }
        if (updatedAccount.getNickname() != null) {
            existingAccount.setNickname(updatedAccount.getNickname());
        }
        if (updatedAccount.getRewards() != null) {
            existingAccount.setRewards(updatedAccount.getRewards());
        }
        if (updatedAccount.getBalance() != null) {
            existingAccount.setBalance(updatedAccount.getBalance());
        }
        //what can we update?
        accountRepository.save(existingAccount);

        return existingAccount;
    }

    public void deleteAccount(Long accountId) {
        Account accountToDelete = getAccountById(accountId);
        accountRepository.delete(accountToDelete);
    }
}
