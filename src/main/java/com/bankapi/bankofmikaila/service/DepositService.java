package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.exception.CustomersNotFoundException;
import com.bankapi.bankofmikaila.exception.DepositByAccountNotFound;
import com.bankapi.bankofmikaila.exception.DepositByIdNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    private final Logger logger = LoggerFactory.getLogger(DepositService.class);

    protected void verifyAccount(Long accountId) throws DepositByAccountNotFound {

        accountRepository.findById(accountId).orElseThrow(()->{
            logger.error("Account with ID: " + accountId + " not found.");
           return new DepositByAccountNotFound("Account not found.");
        });

    }

    protected void verifyDeposit(Long depositId) throws DepositByIdNotFound {

        depositRepository.findById(depositId).orElseThrow(()-> {
            logger.error("Deposit with ID: " + depositId + " not found.");
            return new DepositByIdNotFound("Error fetching deposit with id.");
        });
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

    public Optional<Deposit> getDeposit(Long depositId){

        verifyDeposit(depositId);

        logger.info("Deposit with ID: " + depositId + " retrieved successfully.");
        return depositRepository.findById(depositId);

    }

    public void createDeposit(Deposit deposit, Long accountId) {

        //uses lambda expression to check if account exists (by id), if not throw exception
        Account account = accountRepository.findById(accountId).orElseThrow(()-> {
            logger.error("Account with ID: " + accountId + " not found.");
            return new DepositByAccountNotFound("Error creating deposit: account not found.");
        });
        deposit.setAccount(account);
        account.setBalance(account.getBalance() + deposit.getAmount());
        //Save deposit once null check passes
        //accountRepository.addBalance(accountId, deposit.getAmount()); //hi tanzir - andy & jordy
        logger.info("Deposit created successfully.");
        depositRepository.save(deposit);

    }

    public void updateDeposit(Deposit deposit, Long depositId){

        Optional<Deposit> existingDepositOptional = depositRepository.findById(depositId);

        if(existingDepositOptional.isPresent()){

            Deposit existingDeposit = existingDepositOptional.get();
            existingDeposit.setAmount(deposit.getAmount());
            existingDeposit.setMedium(deposit.getMedium());
            existingDeposit.setDescription(deposit.getDescription());
            existingDeposit.setAccount(deposit.getAccount());
            existingDeposit.setTransactionDate(deposit.getTransactionDate());
            existingDeposit.setStatus(deposit.getStatus());
            existingDeposit.setType(deposit.getType());

            logger.info("Deposit updated successfully.");
            depositRepository.save(existingDeposit);
        } else {
            logger.error("Deposit with ID: " + depositId + " does not exist.");
            throw new DepositByIdNotFound("Deposit ID does not exist.");
        }

    }

    public void deleteDeposit(Long depositId){

        Optional<Deposit> existingDepositOptional = depositRepository.findById(depositId);

        if(existingDepositOptional.isPresent()) {
            logger.info("Deposit successfully deleted.");
            depositRepository.deleteById(depositId);
        } else {
            logger.error("Deposit with ID: " + depositId + " does not exist.");
            throw new DepositByIdNotFound("This id does not exist in deposits.");
        }

    }


}
