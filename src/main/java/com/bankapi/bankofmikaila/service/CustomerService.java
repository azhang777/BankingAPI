package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Customer;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Account getAllCustomerAccounts(Long customerId) {
        return null;
    }


//    public Account createAccount(Long customerId, Account newAccount) {
//        //update their set of accounts
//        Customer customer = customerRepository.getReferenceById(customerId);
//        customer.getAccounts().add(newAccount);
//        return newAccount;
//    }
}
