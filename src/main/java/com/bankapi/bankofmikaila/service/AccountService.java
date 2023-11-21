package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.AccountType;
import com.bankapi.bankofmikaila.exceptions.AccountsNotFoundException;
import com.bankapi.bankofmikaila.exceptions.CustomersNotFoundException;
import com.bankapi.bankofmikaila.exceptions.InvalidTypeException;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    public Account createAccount(Long customerId, Account newAccount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
            logger.error("Customer not found with ID: " + customerId);
            return new CustomersNotFoundException("ERROR ಠ_ಠ ERROR: Customer not found");
        });

        if (newAccount.getType() != AccountType.CHECKING &&
                newAccount.getType() != AccountType.CREDIT &&
                newAccount.getType() != AccountType.SAVINGS) {
            logger.error("Error with creating account.");
            throw new InvalidTypeException("ERROR ಠ_ಠ ERROR: must be SAVINGS, CHECKING, CREDIT");
        }

        newAccount.setCustomer(customer);
        logger.info("Account created successfully.");
        return accountRepository.save(newAccount);
    }

    public Iterable<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            logger.error("No available accounts.");
            throw new AccountsNotFoundException("ERROR ಠ_ಠ ERROR: error fetching accounts");
        }
        logger.info("All accounts retrieved successfully.");
        return accountRepository.findAll();
    }

    public Iterable<Account> getAllCustomerAccounts(Long customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> {
            logger.error("Customer not found with ID: " + customerId);
            return new CustomersNotFoundException("ERROR ಠ_ಠ ERROR: error fetching customers accounts");
        });
        logger.info("All accounts for Customer: " + customerId + " retrieved successfully.");
        return accountRepository.findByCustomer_Id(customerId);
    }

    public static Account getAccountById(Long accountId) {
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
