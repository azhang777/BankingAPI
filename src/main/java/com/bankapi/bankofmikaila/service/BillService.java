package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return (List<Bill>) billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public Bill createBill(Bill bill) {

        return billRepository.save(bill);
    }

    public Optional<Bill> updateBill(Long id, Bill updatedBill) {
        Optional<Bill> existingBill = billRepository.findById(id);

        if (existingBill.isPresent()) {
            updatedBill.setId(existingBill.get().getId());
            return Optional.of(billRepository.save(updatedBill));
        } else {
            // Handle not found case, maybe throw an exception
            return Optional.empty();
        }
    }

    public boolean deleteBill(Long id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Custom method to associate a bill with an account
    public Bill createBillForAccount(Account account, Bill bill) {

        bill.setAccount(account);

        return billRepository.save(bill);
    }

}
