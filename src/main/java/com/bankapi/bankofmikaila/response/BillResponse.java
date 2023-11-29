package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import org.springframework.stereotype.Component;
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
import java.util.Set;
import java.util.stream.Collectors;



@Component
public class BillResponse {

    @Autowired
    private BillService billService;

    public ResponseEntity<?> getAllBills(Long accountId) {
        List<Bill> bills = billService.getAllBills(accountId);
        Detail detail = new Detail();
        detail.setData(bills);
        detail.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    public ResponseEntity<?> getBillById(Long billId) {
        Bill bill = billService.getBillById(billId);
        Detail detail = new Detail();
        if (bill != null) {
            detail.setData(bill);
            detail.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(detail, HttpStatus.OK);
        } else {
            detail.setMessage("Bill not found");
            return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getBillsByCID(Long customerId) {
        Set<Bill> bills = billService.getBillsByCID(customerId);
        Detail detail = new Detail();
        detail.setData(bills);
        detail.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> createBill(Bill bill, Long accountId) {
        Detail detail = new Detail();
        Bill createdBill = billService.createBill(bill, accountId);
        detail.setData(createdBill);
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Created bill and added it to the account");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> updateBill(Long id, Bill bill) {
        Detail detail = new Detail();
        billService.updateBill(id, bill);
        detail.setMessage("Accepted bill modification");
        return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<?> deleteBill(Long billId) {
        Detail detail = new Detail();
        detail.setMessage("Bill deleted successfully");
        detail.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(detail, HttpStatus.NO_CONTENT);
    }
}

