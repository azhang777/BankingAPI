package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.enumeration.TransactionStatus;
import com.bankapi.bankofmikaila.exception.DepositByAccountNotFound;
import com.bankapi.bankofmikaila.exception.DepositByIdNotFound;
import com.bankapi.bankofmikaila.exception.TransactionStatusNotValidException;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    protected void verifyAccount(Long accountId) throws DepositByAccountNotFound {

        accountRepository.findById(accountId).orElseThrow(()->{
            logger.error("Account with ID: " + accountId + " not found.");
            return new DepositByAccountNotFound("Account not found.");
        });

    }

    protected void verifyDeposit(Long depositId) throws DepositByIdNotFound {

        transactionRepository.findById(depositId).orElseThrow(()-> {
            logger.error("Deposit with ID: " + depositId + " not found.");
            return new DepositByIdNotFound("Error fetching deposit with id.");
        });
    }

    public Iterable<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }


    public Iterable<Deposit> getAllDeposits(Long accountId){

        verifyAccount(accountId);

        if(transactionRepository.getAllDepositsByAID(accountId) == null){
            logger.error("Error fetching all deposits with account ID: " + accountId);
            throw new DepositByIdNotFound("Error fetching all deposits");
        }

        logger.info("All deposits retrieved successfully.");
        return transactionRepository.getAllDepositsByAID(accountId); //small changes, using transaction repo instead of deposit repo

    }

    public Optional<Transaction> getDeposit(Long depositId){

        verifyDeposit(depositId);

        logger.info("Deposit with ID: " + depositId + " retrieved successfully.");
        return transactionRepository.findById(depositId);

    }

    public void createDeposit(Deposit deposit, Long accountId) {

        //uses lambda expression to check if account exists (by id), if not throw exception
        Account account = accountRepository.findById(accountId).orElseThrow(()-> {
            logger.error("Account with ID: " + accountId + " not found.");
            return new DepositByAccountNotFound("Error creating deposit: account not found.");
        });
        deposit.setStatus(TransactionStatus.PENDING);
        deposit.setAccount(account);
        account.setBalance(account.getBalance() + deposit.getAmount());
        //Save deposit once null check passes
        //accountRepository.addBalance(accountId, deposit.getAmount()); //hi tanzir - andy & jordy
        logger.info("Deposit created successfully.");
        transactionRepository.save(deposit);

    }

    public void updateDeposit(Deposit updatedDeposit, Long depositId){

        Transaction ogDeposit = transactionRepository.findById(depositId).orElseThrow(()-> {
            logger.error("Deposit with ID: " + depositId + " does not exist.");
            return new DepositByIdNotFound("Deposit ID does not exist.");
        });

        Double ogDepositAmount = ogDeposit.getAmount();
        if(updatedDeposit.getStatus() == TransactionStatus.PENDING) {
            ogDeposit.setAmount(updatedDeposit.getAmount());
            ogDeposit.setMedium(updatedDeposit.getMedium());
            ogDeposit.setDescription(updatedDeposit.getDescription());
            ogDeposit.setTransactionDate(updatedDeposit.getTransactionDate());
            ogDeposit.setStatus(updatedDeposit.getStatus());
            ogDeposit.setType(updatedDeposit.getType());

            Account account = ogDeposit.getAccount();
            account.setBalance(account.getBalance() + (ogDeposit.getAmount() - ogDepositAmount));

            logger.info("Deposit updated successfully.");
            transactionRepository.save(ogDeposit);
        } else {
            logger.error("Deposit status is invalid.");
            throw new TransactionStatusNotValidException("Deposit status is invalid.");
        }

    }

    public void deleteDeposit(Long depositId){

        Transaction transaction = transactionRepository.findById(depositId).orElseThrow(()-> {
            logger.error("Deposit with ID: " + depositId + " does not exist.");
            return new DepositByIdNotFound("This id does not exist in deposits.");
        });

        if(transaction.getStatus() == TransactionStatus.PENDING){
            Account account = transaction.getAccount();
            account.setBalance(account.getBalance() - transaction.getAmount());
            logger.info("Deposit successfully cancelled.");
            transactionRepository.deleteById(depositId);
        } else{
            logger.error("Deposit status is invalid.");
            throw new TransactionStatusNotValidException("Deposit status is invalid.");
        }

    }




}
