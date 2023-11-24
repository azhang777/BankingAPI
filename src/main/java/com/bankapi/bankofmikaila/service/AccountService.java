package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.AccountType;
import com.bankapi.bankofmikaila.exception.AccountsNotFoundException;
import com.bankapi.bankofmikaila.exception.CustomersNotFoundException;
import com.bankapi.bankofmikaila.exception.InvalidTypeException;
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
    //private final Logger logger = LoggerFactory.getLogger(AccountService.class);
    public Account createAccount(Long customerId, Account newAccount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
            //logger.error("Customer with ID:" + customerId + " not found.");
            return new CustomersNotFoundException("ERROR ಠ_ಠ ERROR: Customer not found");
        });
        //could call getCustomerById from customer service to keep DRY, but more method calls

        //this is already checked by http not readable. I do not know how to get this exception prioritized over it.
        if (newAccount.getType() != AccountType.CHECKING && newAccount.getType() != AccountType.CREDIT && newAccount.getType() != AccountType.SAVINGS) {
            //logger.error("Error with creating account.");
            throw new InvalidTypeException("ERROR ಠ_ಠ ERROR: must be SAVINGS, CHECKING, CREDIT");
        }

        newAccount.setCustomer(customer);
        //logger.info("Account created successfully.");
        return accountRepository.save(newAccount);
    }

    public Iterable<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
           // logger.error("List of accounts empty.");
            throw new AccountsNotFoundException("ERROR ಠ_ಠ ERROR: error fetching accounts");
        }
       // logger.info("All accounts retrieved successfully.");
        return accountRepository.findAll();
    }

    public Iterable<Account> getAllCustomerAccounts(Long customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> {
       //     logger.error("Customer with ID:" + customerId + " not found.");
            return new CustomersNotFoundException("ERROR ಠ_ಠ ERROR: error fetching customers accounts");
        });
      //  logger.info("All accounts for Customer:" + customerId + " retrieved successfully.");
        return accountRepository.findByCustomer_Id(customerId);
    }

    public Account getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->{
          //logger.error("Customer with ID:" + accountId + " not found.");
            return new AccountsNotFoundException("ERROR ಠ_ಠ ERROR: error fetching account");
        });

        //logger.info("Account retrieved successfully.");
        return account;
    }

    public Account updateAccount(Long accountId, Account updatedAccount) {
        Account existingAccount = getAccountById(accountId);
        if (updatedAccount.getType().equals(AccountType.SAVINGS) || updatedAccount.getType().equals(AccountType.CHECKING) || updatedAccount.getType().equals(AccountType.CREDIT)) {
            existingAccount.setType(updatedAccount.getType());
        }
        if (updatedAccount.getNickname() != null) {
        //    logger.info("account nickname updated");
            existingAccount.setNickname(updatedAccount.getNickname());
        }
        if (updatedAccount.getRewards() != null) {
          //  logger.info("account rewards updated");
            existingAccount.setRewards(updatedAccount.getRewards());
        }
        if (updatedAccount.getBalance() != null) {
          //  logger.info("account balance updated");
            existingAccount.setBalance(updatedAccount.getBalance());
        }
        //do we need to update the id or keep it
        //what can we update?
       //logger.info("Account updated successfully.");
        accountRepository.save(existingAccount);

        return existingAccount;
    }

    public void deleteAccount(Long accountId) {
        Account accountToDelete = getAccountById(accountId);
        accountRepository.delete(accountToDelete);
     //   logger.info("Account deleted successfully.");
    }
}
