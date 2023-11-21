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
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class WithdrawlService {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionFactory transactionFactory;

    @Autowired
    private TransactionRepository transactionRepository;

    protected void verifyAccount(Long aid) throws WithdrawlsByAccountNotFound {
        Optional<Account> account = accountRepository.findById(aid);

        if(account == null){
            throw new WithdrawlsByAccountNotFound("Account not found");
        }
    } protected void verifyWithdrawal(Long wid) throws WithdrawalByIdNotFound {
        Optional<Withdrawl> withdrawal = withdrawRepo.findById(wid);

        if(withdrawal == null){
            throw new WithdrawalByIdNotFound("error fetching withdrawal with id: " + wid );
        }
    }

    public Iterable<Withdrawl> getAllWithdrawlsByAID(Long accountId){
        verifyAccount(accountId);
      return   withdrawRepo.findWithdrawlsByAccountId(accountId);
    }



    public Withdrawl getWithdrawlById(Long withdrawlId){
        verifyWithdrawal(withdrawlId);
        return withdrawRepo.findById(withdrawlId).get();
    }

@Transactional
    public Transaction createWithdrawl( Long accountId,  TransactionStatus status, TransactionMedium medium,
                                     Double amount, String description){
        var account = accountRepository.findById(accountId);

        if(account.isEmpty()){
            throw  new WithdrawlsByAccountNotFound("Error creating withdrawal: Account not found");
        }
        Withdrawl withdrawl = new Withdrawl();
        withdrawl.setAccount(account.get());

        withdrawl.setStatus(status);
        withdrawl.setMedium(medium);
        withdrawl.setAmount(amount);
        withdrawl.setDescription(description);


        Transaction transaction =  transactionFactory.createTransaction(account.get(), TransactionType.WITHDRAWAL, status, medium, amount,description);

        withdrawl.setId(transaction.getId());
        withdrawl.setType(transaction.getType());
        withdrawl.setTransactionDate(transaction.getTransactionDate());
        accountRepository.updateBalance(accountId, withdrawl.getAccount().getBalance() - withdrawl.getAmount());
        withdrawRepo.save(withdrawl);
        transactionRepository.save(transaction);

    return transaction;
    }


//    @Transactional
//    public Transaction createWithdrawlTransaction(Account payerAccount,
//                                                  TransactionStatus status, TransactionMedium medium,
//                                                  Double amount, String description) {
//        // Use the transaction factory to create the withdrawal transaction
//        Transaction transaction = transactionFactory.createTransaction(payerAccount, TransactionType.WITHDRAWAL, status, medium, amount, description);
//
//        // Deduct the balance in the account
//        accountRepository.deductBalance(payerAccount.getId(), amount);
//
//        // Save the transaction
//        transactionRepository.save(transaction);
//
//        return transaction;
//    }


  public void updateWithdrawl(Withdrawl withdrawl, Long withdrawlId){
      if(withdrawlId == null){
          throw new WithdrawalByIdNotFound("Withdrawal ID does not exist");
      }
    var xWithdrawal = transactionRepository.findById(withdrawlId).get();
    xWithdrawal.setAmount(withdrawl.getAmount());
    xWithdrawal.setMedium(withdrawl.getMedium());
    xWithdrawal.setDescription(withdrawl.getDescription());
    xWithdrawal.setAccount(withdrawl.getAccount());
    xWithdrawal.setTransactionDate(withdrawl.getTransactionDate());
    xWithdrawal.setStatus(withdrawl.getStatus());
    xWithdrawal.setType(withdrawl.getType());




    transactionRepository.save(xWithdrawal);


  }

    public void deleteWithdrawals(Long id) {
        if(id == null){
            throw new WithdrawalByIdNotFound("This id does not exist in withdrawals");
        }
        withdrawRepo.deleteById(id);

    }


}
