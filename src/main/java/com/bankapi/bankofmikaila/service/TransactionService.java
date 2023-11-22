package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.bankapi.bankofmikaila.dto.TransactionStatus;
import com.bankapi.bankofmikaila.dto.TransactionType;
import com.bankapi.bankofmikaila.exceptions.AccountsNotFoundException;
import com.bankapi.bankofmikaila.exceptions.WithdrawlsByAccountNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawlService withdrawlService;



    protected void verifyAccount(Long aid) throws AccountsNotFoundException {
        Optional<Account> account = accountRepository.findById(aid);

        if(account.isEmpty()){
            throw new AccountsNotFoundException("Account not found");
      }

//        else else if (transaction.getType() == TransactionType.DEPOSIT && account.isEmpty()) {
//            throw new AccountsNotFoundException("Error creating deposit: Account id not found ");
//
//        }


    }


    protected void verifyTransaction(Long tid){
        var transaction = transactionRepository.findById(tid).get();





    }





    public Iterable<Transaction> getAllTransactionsByAccountId(Long accountId){
    verifyAccount(accountId);
        return transactionRepository.getAllTransactionsByAID(accountId);
    }

    public Transaction getTransactionById(Long transactionId){


        return transactionRepository.findById(transactionId).get();
    }





    private Transaction createTransaction(Account account, TransactionType type,
                                          TransactionStatus status, TransactionMedium medium,
                                          Double amount, String description) {


        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setAccount(account);
        transaction.setTransactionDate(LocalDateTime.now().toString()); // You might want to use a proper date format
        transaction.setStatus(status);
        transaction.setMedium(medium);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        return transactionRepository.save(transaction);
    }




    @Transactional
    public Transaction createP2PTransaction(Account payerAccount, Account payeeAccount,
                                            TransactionStatus status, TransactionMedium medium,
                                            Double amount, String description) {
//        depositService.createDeposit();

        Transaction transaction = createTransaction(payerAccount, TransactionType.P2P, status, medium, amount, description);
        accountRepository.deductBalance(payerAccount.getId(), amount);
        accountRepository.addBalance(payeeAccount.getId(), amount);
        return transaction;
    }

    @Transactional
    public Transaction createDepositTransaction(Account account,
                                                TransactionStatus status, TransactionMedium medium,
                                                Double amount, String description) {
        Transaction transaction = createTransaction(account, TransactionType.DEPOSIT, status, medium, amount, description);
        accountRepository.addBalance(account.getId(), amount);
        return transaction;
    }

    @Transactional
    public Transaction createWithdrawalTransaction(Account payerAccount,
                                                   TransactionStatus status, TransactionMedium medium,
                                                   Double amount, String description) {
        Transaction transaction = createTransaction(payerAccount, TransactionType.WITHDRAWAL, status, medium, amount, description);
        accountRepository.deductBalance(payerAccount.getId(), amount);
        return transaction;
    }



}
