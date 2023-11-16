package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WithdrawlService {

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private AccountRepository accountRepository;



    public Iterable<Withdrawl> getAllWithdrawlsByAID(Long accountId){
      return   withdrawRepo.findWithdrawlsByAccountId(accountId);
    }




    public void deleteWithdrawals(Long id) {
        withdrawRepo.deleteById(id);

    }


}
