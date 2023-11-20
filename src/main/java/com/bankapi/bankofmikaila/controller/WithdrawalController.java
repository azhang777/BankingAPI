package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.exceptions.WithdrawlsByAccountNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import com.bankapi.bankofmikaila.response.WithdrawlResponse;
import com.bankapi.bankofmikaila.service.WithdrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawlResponse withdrawlResponse;

    @Autowired
    private WithdrawlService withdrawlService;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WithdrawRepo withdrawRepo;

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {

        return withdrawlResponse.getAllWithdrawlsResponse(accountId);
    }
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {

        return withdrawlResponse.getWithdrawalByIdResponse(withdrawalId);
    }


    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawl withdrawal, @PathVariable Long accountId){


    return   withdrawlResponse.createWithdrawal(withdrawal, accountId);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawl withdrawl, @PathVariable Long withdrawalId){

        withdrawalId = withdrawl.getId();
       return withdrawlResponse.updateWithdrawal(withdrawl, withdrawalId );


    }
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@RequestBody Withdrawl withdrawl){

        return withdrawlResponse.deleteWithdrawal(withdrawl);
    }


}
