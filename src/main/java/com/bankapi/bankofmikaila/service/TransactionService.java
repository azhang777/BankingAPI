package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.enumeration.TransactionStatus;
import com.bankapi.bankofmikaila.exception.*;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    protected void verifyAccount(Long accountId) throws DepositByAccountNotFound {

        accountRepository.findById(accountId).orElseThrow(() -> {
            logger.error("Account with ID: " + accountId + " not found.");
            return new DepositByAccountNotFound("Account not found.");
        });

    }

    protected void verifyDeposit(Long depositId) throws DepositByIdNotFound {

        transactionRepository.findById(depositId).orElseThrow(() -> {
            logger.error("Deposit with ID: " + depositId + " not found.");
            return new DepositByIdNotFound("Error fetching deposit with id.");
        });
    }

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    protected void verifyWithdrawalAccountId(Long aid) throws WithdrawalsByAccountNotFound {
        Optional<Account> account = accountRepository.findById(aid);

        if (account == null) {
            logger.error("Account Id not found");
            throw new WithdrawalsByAccountNotFound("Account not found");
        }
    }

    protected void verifyWithdrawal(Long wid) throws WithdrawalByIdNotFound {
        Optional<Transaction> withdrawal = transactionRepository.findById(wid);

        if (withdrawal.isEmpty()) {
            logger.error("Withdrawal id not found");
            throw new WithdrawalByIdNotFound("error fetching withdrawal with id: " + wid);
        }
    }


    public Iterable<Transaction> getAllWithdrawalsByAID(Long accountId) {
        verifyAccount(accountId);

        if (transactionRepository.getAllWithdrawalsByAID(accountId) == null) {
            logger.error("Error fetching all withdrawals with account ID: " + accountId);
            throw new WithdrawalByIdNotFound("Error fetching all withdrawals");
        } else if(transactionRepository.getAllWithdrawalsByAID(accountId).size()==0){
            logger.error("No withdrawals have been made yet.");
            throw new WithdrawalByIdNotFound("No withdrawals have been made yet.");
        }
        return transactionRepository.getAllWithdrawalsByAID(accountId);
    }


    public Transaction getWithdrawalById(Long withdrawalId) {
        verifyWithdrawal(withdrawalId);
        return transactionRepository.findById(withdrawalId).get();
    }


    public Transaction createWithdrawal(Transaction withdrawal, Long accountId) {
        var accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isEmpty()) {
            logger.error("Account Id not found");
            throw new WithdrawalsByAccountNotFound("Error creating withdrawal: Account not found");
        }

        AtomicReference<Transaction> withdrawalRef = new AtomicReference<>(withdrawal);
        withdrawalRef.get().setAccount(accountOptional.get());

        scheduler.schedule(() -> {
            Transaction scheduledWithdrawal = withdrawalRef.get();
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

        verifyWithdrawal(id);
        var transaction = transactionRepository.findById(id).get();
        if (transaction.getStatus() == TransactionStatus.PENDING) {
            transaction.setStatus(TransactionStatus.CANCELLED);

            transactionRepository.save(transaction);

            logger.info("transaction deleted");

        } else {
            logger.error("Withdrawal status is invalid");
            throw new TransactionStatusNotValidException("Withdrawal status is invalid.");

        }

    }


    @Transactional
    public void updateWithdrawal(Transaction withdrawal, Long withdrawalId) {
        Optional<Transaction> xWithdrawalOp = Optional.ofNullable(entityManager.find(Transaction.class, withdrawalId));

        if (xWithdrawalOp.isPresent() && xWithdrawalOp.get().getStatus() == TransactionStatus.PENDING) {
            var xWithdrawal = xWithdrawalOp.get();

            // Update fields with non-null values
            if (withdrawal.getAmount() != null) {
                xWithdrawal.setAmount(withdrawal.getAmount());
            }
            if (withdrawal.getMedium() != null) {
                xWithdrawal.setMedium(withdrawal.getMedium());
            }
            if (withdrawal.getDescription() != null) {
                xWithdrawal.setDescription(withdrawal.getDescription());
            }
            if (withdrawal.getAccount() != null) {
                xWithdrawal.setAccount(withdrawal.getAccount());
            }
            if (withdrawal.getTransactionDate() != null) {
                xWithdrawal.setTransactionDate(withdrawal.getTransactionDate());
            }
            if (withdrawal.getStatus() != null) {
                xWithdrawal.setStatus(withdrawal.getStatus());
            }
            if (withdrawal.getType() != null) {
                xWithdrawal.setType(withdrawal.getType());
            }

            // No need to call transactionRepository.save(xWithdrawal) as EntityManager manages the entity state.

        } else {
            throw new TransactionStatusNotValidException("Status not valid");
        }
    }


    public Iterable<Transaction> getAllDeposits(Long accountId) {

        verifyAccount(accountId);

        if (transactionRepository.getAllDepositsByAID(accountId) == null) {
            logger.error("Error fetching all deposits with account ID: " + accountId);
            throw new DepositByIdNotFound("Error fetching all deposits");
        } else if(transactionRepository.getAllDepositsByAID(accountId).size()==0){
            logger.error("No deposits have been made yet.");
            throw new DepositByIdNotFound("No deposits have been made yet.");
        }

        logger.info("All deposits retrieved successfully.");
        return transactionRepository.getAllDepositsByAID(accountId); //small changes, using transaction repo instead of deposit repo

    }

    public Optional<Transaction> getDeposit(Long depositId) {

        verifyDeposit(depositId);

        logger.info("Deposit with ID: " + depositId + " retrieved successfully.");
        return transactionRepository.findById(depositId);

    }

    public Transaction createDeposit(Transaction deposit, Long accountId) {

        var accountOptional = accountRepository.findById(accountId);

        if(accountOptional.isEmpty()){
            logger.error("Account Id not found");
            throw  new WithdrawalsByAccountNotFound("Error creating withdrawal: Account not found");
        }

        AtomicReference<Transaction> depositRef = new AtomicReference<>(deposit);
        depositRef.get().setAccount(accountOptional.get());

        scheduler.schedule(() -> {
            Transaction scheduledDeposit = depositRef.get();
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

    public void updateDeposit(Transaction updatedDeposit, Long depositId) {

        Transaction ogDeposit = transactionRepository.findById(depositId).orElseThrow(() -> {
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

    public void deleteDeposit(Long depositId) {

        Transaction transaction = transactionRepository.findById(depositId).orElseThrow(() -> {
            logger.error("Deposit with ID: " + depositId + " does not exist.");
            return new DepositByIdNotFound("This id does not exist in deposits.");
        });

        if (transaction.getStatus() == TransactionStatus.PENDING) {
            Account account = transaction.getAccount();
            account.setBalance(account.getBalance() - transaction.getAmount());
            logger.info("Deposit successfully cancelled.");
            transactionRepository.deleteById(depositId);
        } else {
            logger.error("Deposit status is invalid.");
            throw new TransactionStatusNotValidException("Deposit status is invalid.");
        }

    }

    //schedule this
    public Transaction createP2P(Long payerId, Long payeeId, Transaction p2p) {
        Account payer = accountService.getAccountById(payerId);
        Account payee = accountService.getAccountById(payeeId);
        p2p.setAccount(payer);
        p2p.setAccount2(payee);

        payer.setBalance(payer.getBalance() - p2p.getAmount());
        payee.setBalance(payee.getBalance() + p2p.getAmount());

        accountRepository.save(payer);
        accountRepository.save(payee);

        return transactionRepository.save(p2p);
    }


    /*
    get all transactions
    get all p2p
    get p2p by account (payee or payer maybe)
    udpate p2p
    no way to delete p2p right?
     */
}
