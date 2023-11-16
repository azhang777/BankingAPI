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

    public ResponseEntity<?> createDeposit(Deposit deposit) {
        Detail detail = new Detail();
        detail.setData(depositService.getDeposit(deposit.getId()));
        detail.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }




}
