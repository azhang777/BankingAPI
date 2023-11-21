package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.exceptions.DepositByAccountNotFound;
import com.bankapi.bankofmikaila.exceptions.DepositByIdNotFound;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import com.bankapi.bankofmikaila.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepositResponse {

    @Autowired
    DepositService depositService;
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    AccountRepository accountRepository;


    protected void verifyAccount(Long accountId) throws DepositByAccountNotFound {
        Optional<Account> account = accountRepository.findById(accountId);

        if(account == null){
            throw new DepositByAccountNotFound("Account not found");
        }

    }

    protected void verifyDeposit(Long depositId) throws DepositByIdNotFound {
        Optional<Deposit> deposit = depositRepository.findById(depositId);

        if(deposit == null){
            throw new DepositByIdNotFound("error fetching withdrawal with id: " + depositId );
        }
    }


    public ResponseEntity<?> getAllDeposits(Long accountId) {
        Detail detail = new Detail();
        detail.setData(depositService.getAllDeposits(accountId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - All deposits retrieved.");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    public ResponseEntity<?> getDeposit(Long depositId) {
        Detail detail = new Detail();
        detail.setData(depositService.getDeposit(depositId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - deposit id: " + depositId + " retrieved.");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> createDeposit(Deposit deposit, Long accountId) {
        Detail detail = new Detail();
        depositService.createDeposit(deposit,accountId);
        detail.setData(depositService.getDeposit(deposit.getId()));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Success - deposit successfully created for account #: " + accountId);
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateDeposit(Deposit deposit, Long depositId) {
        Detail detail = new Detail();
        depositService.updateDeposit(deposit, depositId);
        detail.setData(depositService.getDeposit(deposit.getId()));
        detail.setCode(HttpStatus.ACCEPTED.value());
        detail.setMessage("Success - deposit successfully updated for deposit #: " + deposit.getId());
        return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<?> deleteDeposit(Long depositId){
        Detail detail = new Detail();
        depositService.deleteDeposit(depositId);
        detail.setCode(HttpStatus.NO_CONTENT.value());
        detail.setMessage("Success - deposit id: " + depositId + " deleted.");
        return new ResponseEntity<>(detail, HttpStatus.NO_CONTENT);
    }


}
