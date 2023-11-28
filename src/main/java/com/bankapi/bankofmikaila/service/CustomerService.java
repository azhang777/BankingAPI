package com.bankapi.bankofmikaila.service;
import com.bankapi.bankofmikaila.exception.CustomersNotFoundException;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Class CustomerService
 *
 * @Review - Customer service needs a the following methods
 *
 * @createAccount
 * @getAllCustomerAccounts
 *
 *
 *
 * @IMPORTANT - Exceptions not done
 */

@Service
public class CustomerService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository; // Injecting CustomerRepository using @Autowired

   private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    // Handles the GET requests to retrieve all customers
    //@GetMapping


    /**
     * @Method getAllCustomers()
     * I removed unnecessary parameters
     * @Tested - PASSED!
     */
    public List<Customer> getAllCustomers() {
        // Log an info message before retrieving customers
        logger.info("Getting all customers");

        // Retrieve all customers from the repository
        List<Customer> customers = customerRepository.findAll();

        // Check if the list of customers is empty
        if (customers.isEmpty()) {
            // Log an error message
            logger.error("List of customers is empty.");

            // Throw a custom exception (CustomersNotFoundException) if no customers are found
            throw new CustomersNotFoundException("ERROR: No customers found");
        }

        // Log an info message for successful retrieval
        logger.info("All customers retrieved successfully.");

        // Return the list of customers
        return customers;
    }

    /**
     * @Method getCustomerById()
     * @Tested - PASSED!
     */
    public Customer getCustomerById(@PathVariable Long id) {
        // Find a customer by ID
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            // If the customer is found, return it
            Customer customer = customerOptional.get();

            // Log an info message for successful retrieval
            logger.info("Customer with ID " + id + " retrieved successfully.");

            return customer;
        } else {
            // If the customer is not found, log an error and throw a custom exception
            logger.error("Customer with ID " + id + " not found");
            throw new CustomersNotFoundException("Customer with ID " + id + " not found");
        }
    }

    /**
     * @Method createCustomer()
     * @Tested - PASSED!
     */


    /*
    Needs fixing
     */
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        // Validate input data
        if (newCustomer == null || newCustomer.getFirstName() == null || newCustomer.getLastName() == null) {
            // If the provided customer data is invalid, return a bad request response
            return null;
        }
        // Save the new customer to the repository
        Customer savedCustomer = customerRepository.save(newCustomer);
        //savedCustomer.setAddress(newCustomer.getAddress());
        // Return a success response with the new customer's ID and a status of 201 (Created)
        return savedCustomer;
    }

    /**
     * @Method updateCustomer()
     * @Tested - PASSED!
     */

    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        // Find the existing customer by ID in the repository
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        // Check if the customer with the given ID exists
        if (existingCustomerOptional.isPresent()) {
            // If the customer exists, retrieve it from the optional
            Customer existingCustomer = existingCustomerOptional.get();

            // Update the existing customer's details with the provided data
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());

            // Save the updated customer to the repository
            customerRepository.save(existingCustomer);

            // Return a success response with a status of 200 (OK) and a message
            return existingCustomer;
        } else {
            // If the customer with the given ID is not found, return a not found response with a status of 404
            return null;
        }
    }

    public Customer getCustomerByAccountId(@PathVariable Long accountId) {
        // Attempt to find an Account using the account ID in the repository.
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        // Check if the Optional contains a non-null value (i.e., if the account is found).
        if (accountOptional.isPresent()) {
            // Extract the Account object from the Optional.
            Account account = accountOptional.get();

            // Return the Customer associated with the found Account.
            return account.getCustomer();
        } else {
            // If the Optional is empty (account not found), return null.
            return null;
        }
    }
}







