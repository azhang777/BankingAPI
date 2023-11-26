package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.exception.BillByIdNotFound;
import com.bankapi.bankofmikaila.exception.BillsByAccountIdNotFoundException;
import com.bankapi.bankofmikaila.exception.BillsByCustomerIdNotFoundException;
import com.bankapi.bankofmikaila.model.Account;

import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.BillRepository;
import com.bankapi.bankofmikaila.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BillService {
    private static long nextBillId;

@Autowired
private BillRepository billRepository;

 @Autowired
 private AccountRepository accountRepository;
 @Autowired
 private CustomerRepository customerRepository;



    public List<Bill> getAllBills(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            Set<Bill> billsSet = billRepository.findBillsByAccountId(accountId);
            // Convert Set to List
            List<Bill> billsList = new ArrayList<>(billsSet);
            return billsList;
        } else {
            throw new BillsByAccountIdNotFoundException("Error fetching bills");
        }
    }


    public Bill getBillById(Long id) {
        var bill = billRepository.findById(id);
        if (bill.isEmpty()){
            throw new BillsByAccountIdNotFoundException("error fetching bill with id + " id);
        }
        return   bill.get();

    }



    public Set <Bill> getBillsByCID(Long customerId){
     var customer = customerRepository.findById(customerId);
     if(customer.isEmpty()){
    throw new BillsByCustomerIdNotFoundException("error fetching bills");
    }
    return billRepository.findBillsByCustomertId(customerId);
    }


       public Bill createBill(Bill bill, Long accountId){
        Optional<Account> account = accountRepository.findById(accountId);
           {

      if( account.isEmpty()){
     throw new BillsByAccountIdNotFoundException("Error creating bill: Account not found");
     }
      bill.setAccount(account.get());
     return billRepository.save(bill);
}


    public Bill createBill(Bill bill) {

        return billRepository.save(bill);
    }


    public Bill updateBill(Long id, Bill updatedBill) {
        Optional<Bill> existingBill = billRepository.findById(id);
               if(existingBill.isPresent()){
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
     }else {
     throw new BillByIdNotFound("Bill ID does not exist");
     }
           }
    }
           public void deleteBill (Long id){
                if (id == null){
                    throw new BillsByAccountIdNotFoundException("This does not exist in bills");
                }
                billRepository.deleteById(id);

            }

            // Custom method to associate a bill with an account
            public Bill createBillForAccount (Account account, Bill bill){

                bill.setAccount(account);

                return billRepository.save(bill);
            }

        }

