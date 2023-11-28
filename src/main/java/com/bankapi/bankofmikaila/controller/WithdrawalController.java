package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WithdrawalController {

    @Autowired
    private TransactionResponse withdrawalResponse;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {

        return withdrawalResponse.getAllWithdrawalsResponse(accountId);
    }
    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId) {

        return withdrawalResponse.getWithdrawalByIdResponse(withdrawalId);
    }


    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@RequestBody Transaction withdrawal, @PathVariable Long accountId){

        return withdrawalResponse.createWithdrawal(withdrawal, accountId);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Transaction withdrawal, @PathVariable Long withdrawalId){

        withdrawalId = withdrawal.getId();
        return withdrawalResponse.updateWithdrawal(withdrawal, withdrawalId );
    }
    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){

        return withdrawalResponse.deleteWithdrawal(withdrawalId);
    }


}
