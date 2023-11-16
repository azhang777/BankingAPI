package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.body.Detail;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerResponse {
    @Autowired
    private CustomerService customerService;

//    public ResponseEntity<?> createAccount(Long customerId, Account newAccount) {
//        Detail detail = new Detail();
//        detail.setData(customerService.createAccount(customerId, newAccount));
//        detail.setCode(HttpStatus.CREATED.value());
//        detail.setMessage("Success - Account created");
//        return new ResponseEntity<>(detail, HttpStatus.CREATED);
//    }
}
