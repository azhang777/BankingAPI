package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.repository.BillRepository;
import com.bankapi.bankofmikaila.service.AccountService;
import com.bankapi.bankofmikaila.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @REVIEW - Delete the billrepository and replace the @RestController with
 *
 * @Component
 *
 *  also delete the mappings on all the methods below
 */
@RestController
public class BillResponse {

    @Autowired
    private BillService billService;
    @Autowired
    private BillRepository billRepository;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<List<Bill>> getAllBillsForAccount(@PathVariable String accountId) {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        Optional<Bill> bill = billService.getBillById(billId);
        return bill.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<List<Bill>> getAllBillsForCustomer(@PathVariable String customerId) {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<Bill> createBillForAccount(@PathVariable String accountId, @RequestBody Bill bill) {


        Bill createdBill = billService.createBillForAccount(accountId, bill);

        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }


    @PutMapping("/bills/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long billId, @RequestBody Bill updatedBill) {
        Optional<Bill> existingBill = billService.updateBill(billId, updatedBill);

        if (existingBill.isPresent()) {
            Bill savedBill = existingBill.get();
            return new ResponseEntity<>(savedBill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable Long billId) {
        boolean deleted = billService.deleteBill(billId);
        if (deleted) {
            return new ResponseEntity<>("Bill deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Bill not found", HttpStatus.NOT_FOUND);
        }
    }
}
