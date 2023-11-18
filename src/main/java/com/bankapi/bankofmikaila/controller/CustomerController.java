package com.bankapi.bankofmikaila.controller;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.response.AccountResponse;
import com.bankapi.bankofmikaila.response.CustomerResponse;
import com.bankapi.bankofmikaila.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    //single responsibility principle - each controller is responsible for a specific set of related functionalities.
    //that is why we separate account and customer controller

    @Autowired
    private CustomerResponse customerResponse;

    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<?> getAllCustomerAccounts(@PathVariable Long customerId) {
        return customerResponse.getAllCustomerAccounts(customerId);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account newAccount) {
        return customerResponse.createAccount(customerId, newAccount);
    }
    @PostMapping("")
    public ResponseEntity<?> createCustomer(@RequestBody Customer newCustomer){
        return customerResponse.createCustomer(newCustomer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer){
        return customerResponse.updateCustomer(customer, customerId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById (@PathVariable Long customerId, @RequestBody Customer customer){
        return customerResponse.getCustomerById(customerId);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers (@RequestBody Customer customer){
        return customerResponse.getAllCustomers(customer);
    }

//still need get customer that owns the specified account
}


