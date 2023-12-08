package com.bankapi.bankofmikaila.service;
import com.bankapi.bankofmikaila.exception.CustomerNotCreatedException;
import com.bankapi.bankofmikaila.exception.CustomerNotFoundByIdException;
import com.bankapi.bankofmikaila.exception.CustomerNotUpdatedException;
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
 * @IMPORTANT - SHOULD NOT BE THROWING EXCEPTIONS, THROWING EXCEPTIONS MEANS FOR ANOTHER METHOD TO HANDLE IT.
 *              THE METHODS HERE SHOULD BE THE ONES HANDLING THE EXCEPTIONS THOUGH.
 *
 *              it seems that the exceptions work, but they are not handled properly. Need to create a method that handles
 *              the custom exceptions within the RestExceptionHandler. Specifically the CustomerNotFoundById and CustomerNotUpdated
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

    public Customer createCustomer(@RequestBody Customer newCustomer) throws CustomersNotFoundException {
        // Validate input data
        if (newCustomer == null || newCustomer.getFirstName() == null || newCustomer.getLastName() == null) {
            // If the provided customer data is invalid, throw a BadRequestException
            throw new CustomerNotCreatedException("Invalid customer data provided. Unable to create customer.");
        }

        // Save the new customer to the repository
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Log success information
        logger.info("Customer created successfully. Customer ID: {}", savedCustomer.getId());

        // Return the saved customer
        return savedCustomer;
    }

    /**
     * @Method updateCustomer()
     * @Tested - PASSED!
     */

    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) throws CustomerNotUpdatedException {
        // Find the existing customer by ID in the repository
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        // Check if the customer with the given ID exists
        if (existingCustomerOptional.isPresent()) {
            // If the customer exists, retrieve it from the optional
            Customer existingCustomer = existingCustomerOptional.get();

            // Update the existing customer's details with the provided data
            if (updatedCustomer.getFirstName() != null) {
                logger.info("Customer first name updated");
                existingCustomer.setFirstName(updatedCustomer.getFirstName());
            }
            if (updatedCustomer.getLastName() != null) {
                logger.info("Customer last name updated");
                existingCustomer.setLastName(updatedCustomer.getLastName());
            }

            // Save the updated customer to the repository
            customerRepository.save(existingCustomer);

            // Log success information
            logger.info("Customer updated successfully. Customer ID: {}", existingCustomer.getId());

            // Return the updated customer
            return existingCustomer;
        } else {
            // If the customer with the given ID is not found, throw a NotFoundException
            throw new CustomerNotUpdatedException("Customer with ID " + id + " not found. Unable to update.");
        }
    }

    public Customer getCustomerByAccountId(@PathVariable Long accountId) throws CustomerNotFoundByIdException {
        // Attempt to find an Account using the account ID in the repository.
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        // Check if the Optional contains a non-null value (i.e., if the account is found).
        if (accountOptional.isPresent()) {
            // Extract the Account object from the Optional.
            Account account = accountOptional.get();

            // Return the Customer associated with the found Account.
            Customer customer = account.getCustomer();

            // Log success information
            logger.info("Customer retrieved successfully. Customer ID: {}", customer.getId());

            return customer;
        } else {
            // If the Optional is empty (account not found), throw a NotFoundException
            throw new CustomerNotFoundByIdException("Account with ID: " + accountId + " not found. Unable to retrieve customer.");
        }
    }
}




