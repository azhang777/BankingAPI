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
     * @Tested - PASSED!
     */
    public ResponseEntity<?> createCustomer(Customer newCustomer) {
        // Create a new Detail object to structure the response.
        Detail detail = new Detail();

        // Set the data field in Detail to the result of creating a new customer account.
        detail.setData(customerService.createCustomer(newCustomer));

        // Set the HTTP status code in Detail to CREATED (201).
        detail.setCode(HttpStatus.CREATED.value());

        // Set the message in Detail to indicate the successful creation of the customer account.
        detail.setMessage("Customer Account Created " + newCustomer);

        // Return a ResponseEntity containing the Detail object and HTTP status CREATED.
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }


    /**
     * @Method updateCustomer()
     * I renamed parameter from customerId to id to avoid error
     * @Tested - PASSED!
     */
    public ResponseEntity<?> updateCustomer(Customer customer, Long customerId) {
        // Create a new Detail object to structure the response.
        Detail detail = new Detail();

        // Set the data field in Detail to the result of updating the customer account.
        detail.setData(customerService.updateCustomer(customerId, customer));

        // Set the HTTP status code in Detail to CREATED (201).
        detail.setCode(HttpStatus.CREATED.value());

        // Set the message in Detail to indicate the successful update of the customer account.
        detail.setMessage("Customer Account Updated " + customerId + customer);

        // Return a ResponseEntity containing the Detail object and HTTP status CREATED.
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }


    /**
     * @Method getCustomerById()
     * I renamed parameter from customerId to id to avoid error
     * @Tested - PASSED!
     */
    public ResponseEntity<?> getCustomerById(Long customerId) {
        // Create a new Detail object to structure the response.
        Detail detail = new Detail();

        // Set the data field in Detail to the result of getting a customer by ID.
        detail.setData(customerService.getCustomerById(customerId));

        // Set the HTTP status code in Detail to OK (200).
        detail.setCode(HttpStatus.OK.value());

        // Set the message in Detail to indicate the successful retrieval of the customer by ID.
        detail.setMessage("Customer received by ID " + customerId);

        // Return a ResponseEntity containing the Detail object and HTTP status OK.
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    /**
     * @Method getAllCustomers()
     * I removed unnecessary parameters
     * @Tested - PASSED!
     */
    public ResponseEntity<?> getAllCustomers() {
        // Create a new Detail object to structure the response.
        Detail detail = new Detail();


        // Set the data field in Detail to the result of getting all customers.
        detail.setData(customerService.getAllCustomers());

        // Set the HTTP status code in Detail to OK (200).
        List<Customer> customerSet = customerService.getAllCustomers();
        customerSet.forEach(customer -> customer.getAddress().size());
        detail.setData(customerSet);
        detail.setCode(HttpStatus.OK.value());

        // Set the message in Detail to indicate the successful retrieval of all customers.
        detail.setMessage("All Customers received ");

        // Return a ResponseEntity containing the Detail object and HTTP status OK.
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> getCustomerByAccountId(Long accountId){
        Detail detail = new Detail();
        detail.setData(customerService.getCustomerByAccountId(accountId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
}