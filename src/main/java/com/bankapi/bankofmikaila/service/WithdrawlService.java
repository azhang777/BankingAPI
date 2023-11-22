package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.bankapi.bankofmikaila.dto.TransactionStatus;
import com.bankapi.bankofmikaila.dto.TransactionType;
import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.exceptions.WithdrawlsByAccountNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class WithdrawlService {

    private static final Logger log = LoggerFactory.getLogger(WithdrawlService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionFactory transactionFactory;

    @Autowired
    private TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    protected void verifyAccount(Long aid) throws WithdrawlsByAccountNotFound {
        Optional<Account> account = accountRepository.findById(aid);

        if(account == null){
            throw new WithdrawlsByAccountNotFound("Account not found");
        }
    } protected void verifyWithdrawal(Long wid) throws WithdrawalByIdNotFound {
        Optional<Transaction> withdrawal = transactionRepository.findById(wid);

        if(withdrawal == null && withdrawal.get().getType() != TransactionType.WITHDRAWAL){
            throw new WithdrawalByIdNotFound("error fetching withdrawal with id: " + wid );
        }
    }

    public Iterable<Transaction> getAllWithdrawlsByAID(Long accountId){
        verifyAccount(accountId);
      return   transactionRepository.getAllWithdrawalsByAID(accountId);
    }



    public Transaction getWithdrawlById(Long withdrawlId){
        verifyWithdrawal(withdrawlId);
        return transactionRepository.findById(withdrawlId).get();
    }

    @Transactional
    public Transaction createWithdrawal(Long accountId, TransactionStatus status, TransactionMedium medium,
                                        Double amount, String description) {
        var accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isEmpty()) {
            throw new WithdrawlsByAccountNotFound("Error creating withdrawal: Account not found");
        }

        Account account = accountOptional.get();

        // Save the current state of the account before making changes
        Double previousBalance = account.getBalance();

        Withdrawl withdrawl = new Withdrawl();
        withdrawl.setAccount(account);
        withdrawl.setStatus(status);
        withdrawl.setMedium(medium);
        withdrawl.setAmount(amount);
        withdrawl.setDescription(description);

        // Update the account balance
        account.setBalance(previousBalance - amount);
        accountRepository.save(account);

        // Create the transaction using the factory
        Transaction transaction = transactionFactory.createTransaction(account, TransactionType.WITHDRAWAL, status, medium, amount, description);
        transaction.setPreviousBalance(previousBalance); // Set the previous balance on the transaction

        // Update the Withdrawal entity with the transaction details
        withdrawl.setId(transaction.getId());
        withdrawl.setType(transaction.getType());
        withdrawl.setTransactionDate(transaction.getTransactionDate());

        // Save the transaction and return it
        transactionRepository.save(transaction);

        return transaction;
    }
    @Transactional
    public void updateWithdrawal(Withdrawl existingWithdrawal, Long withdrawalId) {
        // Retrieve the existing withdrawal from the database
        Optional<Transaction> existingWithdrawalOptional = transactionRepository.findById(withdrawalId);

        if (existingWithdrawalOptional.isPresent()) {
            Transaction existing = existingWithdrawalOptional.get();
            updateTransaction(existing, existingWithdrawal);
            transactionRepository.save(existing);
        } else {
            throw new WithdrawalByIdNotFound("Withdrawal ID not found");
        }
    }

    @Transactional
    private void updateTransaction(Transaction existing, Transaction newTransaction) {
        // Fetch the current state of the existing transaction
        double previousAmount = existing.getAmount();
        Account existingAccount = existing.getAccount();
        double previousBalance = existingAccount.getBalance();

        existing.setAmount(newTransaction.getAmount());
        existing.setMedium(newTransaction.getMedium());
        existing.setDescription(newTransaction.getDescription());
        existing.setTransactionDate(newTransaction.getTransactionDate());

        // Ensure the associated Account is managed
        Account newAccount = newTransaction.getAccount();
        if (newAccount != null && newAccount.getId() != null) {
            // If the newAccount has an ID, it means it's an existing account
            Account managedAccount = entityManager.find(Account.class, newAccount.getId());

            // Update the account balance with the previous balance and the new transaction amount
            managedAccount.setBalance(previousBalance + previousAmount - newTransaction.getAmount());

            existing.setAccount(managedAccount);
        }

        existing.setStatus(newTransaction.getStatus());
        existing.setType(newTransaction.getType());
    }

    @Transactional
    public ResponseEntity<String> deleteWithdrawal(Long id) {
        try {
            log.info("Deleting withdrawal with ID: {}", id);

            Optional<Transaction> recordOptional = transactionRepository.findById(id);

            if (recordOptional.isEmpty() || !(recordOptional.get() instanceof Withdrawl)) {
                log.warn("Withdrawal not found with ID: {}", id);
                throw new WithdrawalByIdNotFound("Withdrawal not found with ID: " + id);
            }

            Withdrawl withdrawal = (Withdrawl) recordOptional.get();
            Account account = withdrawal.getAccount();

            // Remove the association between the withdrawal and the account
            account.getTransactions().remove(withdrawal);

            // Delete the withdrawal
            transactionRepository.delete(withdrawal);

            log.info("Withdrawal deleted successfully");
            return ResponseEntity.ok("Withdrawal deleted successfully");
        } catch (WithdrawalByIdNotFound e) {
            log.error("WithdrawalByIdNotFound: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Exception during withdrawal deletion", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
