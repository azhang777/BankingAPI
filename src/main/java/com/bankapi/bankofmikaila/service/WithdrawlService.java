package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class WithdrawlService {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private AccountRepository accountRepository;



    protected void verifyWithdrawlId(Long withdrawlId) throws EntityNotFoundException{

        var withdrawl = withdrawRepo.findById(withdrawlId);

        if(withdrawl.isEmpty()){
            throw new EntityNotFoundException("Withdrawal with Id: " + withdrawlId + " not found.");

        }

    }

    protected void verifyAccountId(Long accountId) throws EntityNotFoundException{
        var account = accountRepository.findById(accountId);

        if(account.isEmpty()){
            throw new EntityNotFoundException("Account with Id: " + accountId + " not found.");
        }
    }

//    public Iterable<Withdrawl> getAllWithdrawlsByAID(Long accountId){
//      return   withdrawRepo.findWithdrawlsByAccountId(accountId);
//    }

    public Withdrawl getWithdrawlById(Long withdrawlId){
        return withdrawRepo.findById(withdrawlId).get();
    }


    public void createWithdrawl(Withdrawl withdrawl, Long accountId){
    //checking if the account exisits
       verifyAccountId(accountId);
    withdrawRepo.save(withdrawl);
    }

  public void updateWithdrawl(Withdrawl withdrawl, Long withdrawlId){
    verifyWithdrawlId(withdrawlId);
    var xWithdrawal = withdrawRepo.findById(withdrawlId).get();
    xWithdrawal.setAmount(withdrawl.getAmount());
    xWithdrawal.setMedium(withdrawl.getMedium());
    xWithdrawal.setDescription(withdrawl.getDescription());
    xWithdrawal.setAccount(withdrawl.getAccount());
    xWithdrawal.setTransaction_date(withdrawl.getTransaction_date());
    xWithdrawal.setStatus(withdrawl.getStatus());
    xWithdrawal.setType(withdrawl.getType());


    withdrawRepo.save(xWithdrawal);


  }

    public void deleteWithdrawals(Long id) {
        withdrawRepo.deleteById(id);

    }


}
