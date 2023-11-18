package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.service.AccountService;
import com.bankapi.bankofmikaila.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerResponse {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    public ResponseEntity<?> createAccount(Long customerId, Account newAccount) {
        Detail detail = new Detail();
        detail.setData(accountService.createAccount(customerId, newAccount));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Success - Account created.");

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllCustomerAccounts(Long customerId) {
        Detail detail = new Detail();
        detail.setData(accountService.getAllCustomerAccounts(customerId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - Accounts retrieved for customer " + customerId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> createCustomer(Customer newCustomer) {
        Detail detail = new Detail();
        detail.setData(customerService.createCustomer(newCustomer));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Customer Account Created " + newCustomer);

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCustomer(Customer customer, Long customerId) {
        Detail detail = new Detail();
        detail.setData(customerService.updateCustomer(customerId, customer));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Customer Account Updated " + customerId + customer);

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getCustomerById(Long customerId) {
        Detail detail = new Detail();
        detail.setData(customerService.getCustomerById(customerId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Customer received by ID " + customerId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllCustomers(Customer customer) {
        Detail detail = new Detail();
        detail.setData(customerService.getAllCustomers(customer));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("All Customers received " + customer);

        return  new ResponseEntity<>(detail, HttpStatus.OK);
    }

//    public ResponseEntity<?> createAccount(Long customerId, Account newAccount) {
//        Detail detail = new Detail();
//        detail.setData(customerService.createAccount(customerId, newAccount));
//        detail.setCode(HttpStatus.CREATED.value());
//        detail.setMessage("Success - Account created");
//        return new ResponseEntity<>(detail, HttpStatus.CREATED);
//    }
}
