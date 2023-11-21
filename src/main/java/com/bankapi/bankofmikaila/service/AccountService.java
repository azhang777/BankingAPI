package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.AccountType;
import com.bankapi.bankofmikaila.exceptions.AccountsNotFoundException;
import com.bankapi.bankofmikaila.exceptions.CustomersNotFoundException;
import com.bankapi.bankofmikaila.exceptions.InvalidTypeException;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Class AccountService
 *
 * @Review - Customer service needs the following methods
 *
 * @createAccount
 * @getAllCustomerAccounts
 *
 * @IMPORTANT - We can create an account for a customer that does not exist, fix that shit, may need an exception
 * @IMPORTANT - Can retrieve accounts from customers that do not exist.
 * @IMPORTANT - Update Account works, but body still has data pop up even though it should be null. Resolved
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Account createAccount(Long customerId, Account newAccount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->new CustomersNotFoundException("ERROR ಠ_ಠ ERROR: error fetching customers account"));
        if (newAccount.getType() != AccountType.CHECKING &&
                newAccount.getType() != AccountType.CREDIT &&
                newAccount.getType() != AccountType.SAVINGS) {
            System.out.println("HAO HAO");
            throw new InvalidTypeException("ERROR ಠ_ಠ ERROR: must be SAVINGS, CHECKING, CREDIT");
        }
        newAccount.setCustomer(customer);
        return accountRepository.save(newAccount);
    }

    public Iterable<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            throw new AccountsNotFoundException("ERROR ಠ_ಠ ERROR: error fetching accounts");
        }
        return accountRepository.findAll();
    }

    public Iterable<Account> getAllCustomerAccounts(Long customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> new CustomersNotFoundException("ERROR ಠ_ಠ ERROR: error fetching customers accounts"));
        return accountRepository.findByCustomer_Id(customerId);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountsNotFoundException("ERROR ಠ_ಠ ERROR: error fetching account"));
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
        //do we need to update the id or keep it
        //what can we update?
        accountRepository.save(existingAccount);

        return existingAccount;
    }

    public void deleteAccount(Long accountId) {
        Account accountToDelete = getAccountById(accountId);
        accountRepository.delete(accountToDelete);
    }
}
