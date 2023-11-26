package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Bill;
import com.bankapi.bankofmikaila.response.BillResponse;
import com.bankapi.bankofmikaila.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillResponse billResponse;


    /**
     *
     * @Delete
     */

    //T
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        Optional<Bill> bill = billService.getBillById(billId);
        return bill.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        Bill createdBill = billService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long billId, @RequestBody Bill updatedBill) {
        Optional<Bill> updated = billService.updateBill(billId, updatedBill);
        return updated.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long billId) {
        boolean deleted = billService.deleteBill(billId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
