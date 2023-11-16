package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawlService {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private AccountRepository accountRepository;



    public Iterable<Withdrawl> getAllWithdrawlsByAID(Long accountId){
      return   withdrawRepo.findWithdrawlsByAccountId(accountId);
    }

    public Withdrawl getWithdrawlById(Long withdrawlId){
        return withdrawRepo.findById(withdrawlId).get();
    }


    public Withdrawl createWithdrawl(Long accountId, Withdrawl withdrawl){
    Optional<Account> account = accountRepository.findById(accountId);
    withdrawl.setAccount(account.get());
    return withdrawRepo.save(withdrawl);
    }

    public void deleteWithdrawals(Long id) {
        withdrawRepo.deleteById(id);

    }


}
