package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.repository.BillRepository;
import com.bankapi.bankofmikaila.service.AccountService;
import com.bankapi.bankofmikaila.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BillResponse {

    @Autowired
    private BillService billService;

    public List<Bill> getAllBillsForAccount(String accountId) {
        List<Bill> bills = billService.getAllBills();
        return bills;
    }

    public Bill getBillById(Long billId) {
        Optional<Bill> bill = billService.getBillById(billId);
        return bill.orElse(null);
    }

    public List<Bill> getAllBillsForCustomer(String customerId) {
        List<Bill> bills = billService.getAllBills();
        return bills;
    }

    public Bill createBillForAccount(Account accountId, Bill bill) {
        Bill createdBill = billService.createBillForAccount(accountId, bill);
        return createdBill;
    }

    public ResponseEntity<Bill> updateBill(Long billId, Bill updatedBill) {
        Optional<Bill> existingBill = billService.updateBill(billId, updatedBill);

        if (existingBill.isPresent()) {
            Bill savedBill = existingBill.get();
            return new ResponseEntity<>(savedBill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteBill(Long billId) {
        boolean deleted = billService.deleteBill(billId);
        if (deleted) {
            return new ResponseEntity<>("Bill deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
        }
    }
}