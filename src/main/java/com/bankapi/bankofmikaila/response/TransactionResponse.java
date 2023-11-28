package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;

import com.bankapi.bankofmikaila.exception.DepositByAccountNotFound;
import com.bankapi.bankofmikaila.exception.DepositByIdNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
import com.bankapi.bankofmikaila.exception.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TransactionResponse {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    protected void verifyAccount(Long accountId) throws DepositByAccountNotFound {
        Optional<Account> account = accountRepository.findById(accountId);

        if(account == null){
            throw new DepositByAccountNotFound("Account not found");
        }

    }

    protected void verifyDeposit(Long depositId) throws DepositByIdNotFound {
        Optional<Transaction> deposit = transactionRepository.findById(depositId);

        if(deposit == null){
            throw new DepositByIdNotFound("error fetching withdrawal with id: " + depositId );
        }
    }

    public ResponseEntity<?> getAllTransactions() {
        Detail detail = new Detail();
        detail.setData(transactionService.getAllTransactions());
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - All transactions retrieved.");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllDeposits(Long accountId) {
        Detail detail = new Detail();
        detail.setData(transactionService.getAllDeposits(accountId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - All deposits retrieved.");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    public ResponseEntity<?> getDeposit(Long depositId) {
        Detail detail = new Detail();
        detail.setData(transactionService.getDeposit(depositId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - deposit id: " + depositId + " retrieved.");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> createDeposit(Transaction deposit, Long accountId) {
        Detail detail = new Detail();
        transactionService.createDeposit(deposit,accountId);
        detail.setData(transactionService.getDeposit(deposit.getId()));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Success - deposit successfully created for account #: " + accountId);
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateDeposit(Transaction deposit, Long depositId) {
        Detail detail = new Detail();
        transactionService.updateDeposit(deposit, depositId);
        detail.setCode(HttpStatus.ACCEPTED.value());
        detail.setMessage("Success - deposit successfully updated for deposit #: " + deposit.getId());
        return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteDeposit(Long depositId){
        Detail detail = new Detail();
        transactionService.deleteDeposit(depositId);
        detail.setCode(HttpStatus.NO_CONTENT.value());
        detail.setMessage("Success - deposit id: " + depositId + " deleted.");
        return new ResponseEntity<>(detail, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getAllWithdrawalsResponse(Long accountId){
        Detail detail = new Detail();
        detail.setData(transactionService.getAllWithdrawalsByAID(accountId));
        detail.setCode(HttpStatus.OK.value());


        return new ResponseEntity<>(detail, HttpStatus.OK);

    }

    public ResponseEntity<?> getWithdrawalByIdResponse(Long withdrawalId){
        Detail detail =new Detail();
        detail.setData(transactionService.getWithdrawalById(withdrawalId));
        detail.setCode(HttpStatus.OK.value());

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createWithdrawal(Transaction withdrawal, Long accountId){

        Detail detail = new Detail();
        detail.setData( transactionService.createWithdrawal(withdrawal, accountId));
        detail.setCode(HttpStatus.OK.value());
        return  new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateWithdrawal(Transaction withdrawal, Long withdrawalId ) {
        withdrawalId = withdrawal.getId();  // Assuming Withdrawl has a getId() method

        Detail detail = new Detail();

        try {
            transactionService.updateWithdrawal(withdrawal, withdrawalId);
            detail.setMessage("Accepted withdrawal modification");
            detail.setCode(HttpStatus.ACCEPTED.value());
            return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
        } catch (WithdrawalByIdNotFound e) {
            detail.setMessage("Withdrawal ID not found");
            detail.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> deleteWithdrawal(Long id) {
        try {
            transactionService.deleteWithdrawal(id);
            return ResponseEntity.ok("Withdrawal deleted successfully");
        } catch (WithdrawalByIdNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    public ResponseEntity<?> createP2P(Long payerId, Long payeeId, Transaction p2p) {
        Detail detail = new Detail();
        detail.setData(transactionService.createP2P(payerId, payeeId, p2p));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Success - P2P successfully transferred from payer account:" + payerId + " to payee account" + payeeId);
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }
}
