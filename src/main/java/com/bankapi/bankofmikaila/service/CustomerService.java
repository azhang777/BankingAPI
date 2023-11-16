package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
@RestController
    @RequestMapping("/customers")
    public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository; // Injecting CustomerRepository using @Autowired

    // Handles the GET requests to retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll(); // Retrieve and return all customers from the repository
    }

    // Handles GET requests to retrieve a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id); // Find a customer by ID

        if (customer.isPresent()) {
            // If the customer is found, return it with a status of 200 (OK)
            return ResponseEntity.ok(customer.get());
        } else {
            // If the customer is not found, return a response with a status of 404 (Not Found)
            return ResponseEntity.notFound().build();
        }

    

    }

}





