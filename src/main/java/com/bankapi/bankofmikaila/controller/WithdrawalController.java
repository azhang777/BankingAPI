package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Withdrawal;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import com.bankapi.bankofmikaila.response.WithdrawalResponse;
import com.bankapi.bankofmikaila.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalResponse withdrawlResponse;

    @Autowired
    private WithdrawalService withdrawalService;

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
    public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long accountId){


    return   withdrawlResponse.createWithdrawal(withdrawal, accountId);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawl, @PathVariable Long withdrawalId){

        withdrawalId = withdrawl.getId();
       return withdrawlResponse.updateWithdrawal(withdrawl, withdrawalId );


    }
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){

        return withdrawlResponse.deleteWithdrawal(withdrawalId);
    }


}
