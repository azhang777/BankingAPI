package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.enumeration.TransactionStatus;
import com.bankapi.bankofmikaila.exception.*;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.model.Withdrawal;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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


    protected void verifyWithdrawlAccountId(Long aid) throws WithdrawalsByAccountNotFound {
        Optional<Account> account = accountRepository.findById(aid);

        if(account == null){
            logger.error("Account Id not found");
            throw new WithdrawalsByAccountNotFound("Account not found");
        }
    } protected void verifyWithdrawal(Long wid) throws WithdrawalByIdNotFound {
        Optional<Transaction> withdrawal = transactionRepository.findById(wid);

        if(withdrawal == null){
            logger.error("Withdrawal id not found");
            throw new WithdrawalByIdNotFound("error fetching withdrawal with id: " + wid );
        }
    }


    public Iterable<Withdrawal> getAllWithdrawlsByAID(Long accountId){
        verifyAccount(accountId);
        return transactionRepository.getAllWithdrawalsByAID(accountId);
    }



    public Transaction getWithdrawlById(Long withdrawlId){
        verifyWithdrawal(withdrawlId);
        return transactionRepository.findById(withdrawlId).get();
    }


    public Withdrawal createWithdrawl(Withdrawal withdrawal, Long accountId){
        var accountOptional = accountRepository.findById(accountId);

        if(accountOptional.isEmpty()){
            logger.error("Account Id not found");
            throw  new WithdrawalsByAccountNotFound("Error creating withdrawal: Account not found");
        }

        AtomicReference<Withdrawal> withdrawalRef = new AtomicReference<>(withdrawal);
        withdrawalRef.get().setAccount(accountOptional.get());

        scheduler.schedule(() -> {
            Withdrawal scheduledWithdrawal = withdrawalRef.get();
            if (scheduledWithdrawal != null && scheduledWithdrawal.getStatus() == TransactionStatus.PENDING) {
                // Perform the status update and other operations within a transaction
                transactionRepository.findById(scheduledWithdrawal.getId()).ifPresent(existingWithdrawal -> {
                    existingWithdrawal.setStatus(TransactionStatus.COMPLETED);

                    var account = accountRepository.findById(accountId).get();

                   account.setBalance(accountOptional.get().getBalance() - existingWithdrawal.getAmount());
                    logger.info("Account balance updated");
                    transactionRepository.save(existingWithdrawal);
                    accountRepository.save(account);
                });
            } else {
                logger.info("Withdrawal already deleted or status is already COMPLETED");
            }
        }, 10, TimeUnit.SECONDS);

        return transactionRepository.save(withdrawalRef.get());


    }


    public void deleteWithdrawal(Long id) {
        if(id == null){
            throw new WithdrawalByIdNotFound("This id does not exist in withdrawals");
        } else if (transactionRepository.findById(id).get().getStatus() == TransactionStatus.PENDING){
            transactionRepository.deleteById(id);
            logger.info("transaction deleted");

        }else{
            logger.error("Withdrawal status is invalid");
            throw new TransactionStatusNotValidException("Withdrawal status is invalid.");

        }


    }


    public void updateWithdrawl(Withdrawal withdrawl, Long withdrawlId){

        var xWithdrawalOp = transactionRepository.findById(withdrawlId);


        if(xWithdrawalOp.isPresent()){
            var xWithdrawal = xWithdrawalOp.get();
            xWithdrawal.setAmount(withdrawl.getAmount());
            xWithdrawal.setMedium(withdrawl.getMedium());
            xWithdrawal.setDescription(withdrawl.getDescription());
            xWithdrawal.setAccount(withdrawl.getAccount());
            xWithdrawal.setTransactionDate(withdrawl.getTransactionDate());
            xWithdrawal.setStatus(withdrawl.getStatus());
            xWithdrawal.setType(withdrawl.getType());
            transactionRepository.save(xWithdrawal);

        }else {
            throw new WithdrawalByIdNotFound("Withdrawal Id does not exist");
        }






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

    public Deposit createDeposit(Deposit deposit, Long accountId) {

        var accountOptional = accountRepository.findById(accountId);

        if(accountOptional.isEmpty()){
            logger.error("Account Id not found");
            throw  new WithdrawalsByAccountNotFound("Error creating withdrawal: Account not found");
        }

        AtomicReference<Deposit> depositRef = new AtomicReference<>(deposit);
        depositRef.get().setAccount(accountOptional.get());

        scheduler.schedule(() -> {
            Deposit scheduledDeposit = depositRef.get();
            if (scheduledDeposit != null && scheduledDeposit.getStatus() == TransactionStatus.PENDING) {
                // Perform the status update and other operations within a transaction
                transactionRepository.findById(scheduledDeposit.getId()).ifPresent(existingDeposit -> {
                    existingDeposit.setStatus(TransactionStatus.COMPLETED);

                    var account = accountRepository.findById(accountId).get();
                    account.setBalance(accountOptional.get().getBalance() + existingDeposit.getAmount());
                    logger.info("Account balance updated");
                    transactionRepository.save(existingDeposit);
                    accountRepository.save(account);
                });
            } else {
                logger.info("Deposit already deleted or status is already COMPLETED");
            }
        }, 10, TimeUnit.SECONDS);

        return transactionRepository.save(depositRef.get());

    }

    public void updateDeposit(Deposit updatedDeposit, Long depositId){

        Transaction ogDeposit = transactionRepository.findById(depositId).orElseThrow(()-> {
            logger.error("Deposit with ID: " + depositId + " does not exist.");
            return new DepositByIdNotFound("Deposit ID does not exist.");
        });

        Double ogDepositAmount = ogDeposit.getAmount();
        if(ogDeposit.getStatus() == TransactionStatus.PENDING) {
            ogDeposit.setAmount(updatedDeposit.getAmount());
            ogDeposit.setMedium(updatedDeposit.getMedium());
            ogDeposit.setDescription(updatedDeposit.getDescription());
            ogDeposit.setTransactionDate(updatedDeposit.getTransactionDate());
            ogDeposit.setStatus(TransactionStatus.PENDING);
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
