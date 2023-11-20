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

import java.util.List;
import java.util.Set;

@Component
public class CustomerResponse {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    /**
     * @Method createAccount()
     *
     * @Tested - NOT YET TESTED
     */
    public ResponseEntity<?> createAccount(Long customerId, Account newAccount) {
        Detail detail = new Detail();
        detail.setData(accountService.createAccount(customerId, newAccount));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Success - Account created.");

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    /**
     * @Method getAllCustomerAccounts()
     *
     * @Tested - NOT YET TESTED
     */
    public ResponseEntity<?> getAllCustomerAccounts(Long customerId) {
        Detail detail = new Detail();
        detail.setData(accountService.getAllCustomerAccounts(customerId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - Accounts retrieved for customer " + customerId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    /**
     * @Method createCustomer()
     *
     * @Tested - PASSED!
     */
    public ResponseEntity<?> createCustomer(Customer newCustomer) {
        Detail detail = new Detail();
        detail.setData(customerService.createCustomer(newCustomer));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Customer Account Created " + newCustomer);

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }



    /**
     * @Method updateCustomer()
     * I renamed parameter from customerId to id to avoid error
     *
     * @Tested - PASSED!
     */
    public ResponseEntity<?> updateCustomer(Customer customer, Long customerId) {
        Detail detail = new Detail();
        detail.setData(customerService.updateCustomer(customerId, customer));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Customer Account Updated " + customerId + customer);

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }


    /**
     * @Method getCustomerById()
     * I renamed parameter from customerId to id to avoid error
     *
     * @Tested - PASSED!
     */
    public ResponseEntity<?> getCustomerById(Long customerId) {
        Detail detail = new Detail();
        detail.setData(customerService.getCustomerById(customerId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Customer received by ID " + customerId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    /**
     * @Method getAllCustomers()
     * I removed unnecessary parameters
     *
     * @Tested - PASSED!
     */
    public ResponseEntity<?> getAllCustomers() {
        Detail detail = new Detail();
        List<Customer> customerSet = customerService.getAllCustomers();
        customerSet.forEach(customer -> customer.getAddress().size());
        detail.setData(customerSet);
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("All Customers received ");

        return  new ResponseEntity<>(detail, HttpStatus.OK);
    }


}
