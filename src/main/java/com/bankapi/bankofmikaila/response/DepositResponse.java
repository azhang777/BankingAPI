package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.body.Detail;
import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.service.DepositService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DepositResponse {

    @Autowired
    DepositService depositService;

    public ResponseEntity<?> getAllDeposits() {
        Detail detail = new Detail();
        detail.setData(depositService.getAllDeposits());
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
        detail.setData(depositService.getDeposit(depositId));
        detail.setCode(HttpStatus.NO_CONTENT.value());
        detail.setMessage("Success - deposit id: " + depositId + " deleted.");
        return new ResponseEntity<>(detail, HttpStatus.NO_CONTENT);
    }


}
