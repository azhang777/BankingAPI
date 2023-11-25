package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import com.bankapi.bankofmikaila.response.WithdrawlResponse;
import com.bankapi.bankofmikaila.service.WithdrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawlResponse withdrawlResponse;

    @Autowired
    private WithdrawlService withdrawlService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


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
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawl withdrawl, @PathVariable Long withdrawalId) {
        Detail detail = new Detail();

        try {
            // Call the service to update the withdrawal
            withdrawlService.updateWithdrawal(withdrawl, withdrawalId);

            // Respond with success
            detail.setMessage("Accepted withdrawal modification");
            detail.setCode(HttpStatus.ACCEPTED.value());
            return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
        } catch (WithdrawalByIdNotFound e) {
            // Respond with not found if the withdrawal ID is not found
            detail.setMessage("Withdrawal ID not found");
            detail.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
        }
    }
//    @DeleteMapping("/withdrawals/{withdrawalId}")
//    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){
//
//        return withdrawlResponse.deleteWithdrawal(withdrawalId);
//    }


}
