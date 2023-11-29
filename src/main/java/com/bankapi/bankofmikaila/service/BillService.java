package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.exception.AccountsNotFoundException;
import com.bankapi.bankofmikaila.exception.BillByIdNotFound;
import com.bankapi.bankofmikaila.exception.BillsByAccountIdNotFoundException;
import com.bankapi.bankofmikaila.exception.BillsByCustomerIdNotFoundException;
import com.bankapi.bankofmikaila.model.Account;

import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.BillRepository;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Service
public class BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillService.class);
@Autowired
private BillRepository billRepository;

 @Autowired
 private AccountRepository accountRepository;
 @Autowired
 private CustomerRepository customerRepository;



    public Set<Bill> getBillsForAccountId(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            logger.error("Error in getAllBills method");
            throw new AccountsNotFoundException("Account not found");
        }
        return billRepository.findBillsByAccountId(accountId);

    }




    public Bill getBillById(Long id) {
        try {
            var bill = billRepository.findById(id);
            if (bill.isEmpty()){
                throw new BillByIdNotFound("error fetching bill with id " + id);
            }
            return bill.get();
        } catch (BillByIdNotFound e) {
            logger.error("Error in getBillById method", e);
            throw e;
        }
    }



    public Set<Bill> getBillsByCID(Long customerId) {
        try {
            var customer = customerRepository.findById(customerId);
            if (customer.isEmpty()){
                throw new BillsByCustomerIdNotFoundException("error fetching bills");
            }
            return billRepository.findBillsByCustomertId(customerId);
        } catch (BillsByCustomerIdNotFoundException e) {
            logger.error("Error in getBillsByCID method", e);
            throw e;
        }
    }


    public Bill createBill(Bill bill, Long accountId) {
        try {
            Optional<Account> account = accountRepository.findById(accountId);

            if (account.isEmpty()) {
                throw new BillsByAccountIdNotFoundException("Error creating bill: Account not found");
            }
            bill.setAccount(account.get());
            bill.setCustomer(account.get().getCustomer());
            return billRepository.save(bill);
        } catch (BillsByAccountIdNotFoundException e) {
            logger.error("Error in createBill method", e);
            throw e;
        }
    }


    public void updateBill(Long id, Bill updatedBill) {
        try {
        Optional<Bill> existingBill = billRepository.findById(id);
        if (existingBill.isPresent()) {
            Bill newBill = existingBill.get();
            newBill.setId(updatedBill.getId());
            newBill.setStatus(updatedBill.getStatus());
            newBill.setPayee(updatedBill.getPayee());
            newBill.setNickname(updatedBill.getNickname());
            newBill.setCreationDate(updatedBill.getCreationDate());
            newBill.setPaymentDate(updatedBill.getPaymentDate());
            newBill.setRecurringDate(updatedBill.getRecurringDate());
            newBill.setUpcomingPaymentDate(updatedBill.getUpcomingPaymentDate());
            newBill.setPaymentAmount(updatedBill.getPaymentAmount());
            newBill.setAccount(updatedBill.getAccount());
            newBill.setCustomer(updatedBill.getCustomer());
            billRepository.save(newBill);
        } else {
            throw new BillByIdNotFound("Bill ID does not exist");
        } } catch (BillByIdNotFound e) {
            logger.error("Error in updateBill method", e);
            throw e;
        }
    }
    public void deleteBill(Long id) {
        try {
            if (id == null) {
                throw new BillByIdNotFound("This does not exist in bills");
            }
            billRepository.deleteById(id);
        } catch (BillByIdNotFound e) {
            logger.error("Error in deleteBill method", e);
            throw e;
        }
    }




    // Custom method to associate a bill with an account
    public Bill createBillForAccount (Account account, Bill bill){
        try {
            bill.setAccount(account);
            return billRepository.save(bill);
        } catch (AccountsNotFoundException e) {
            logger.error("Error in createBillForAccount method", e);
            throw e;
        }
    }

}





