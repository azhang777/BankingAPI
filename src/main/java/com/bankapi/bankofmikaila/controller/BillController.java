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
public class BillController {
    @Autowired // Auto wire the BillResponse bean, presumably defined elsewhere in the application.
    private BillResponse billResponse;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsByAccountId(@PathVariable Long accountId) {
        return billResponse.getAllBills(accountId); //call to the BillResponse service to handle the request to get all bills by account.
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {
        return billResponse.getBillById(billId);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long accountId) {
        return billResponse.createBill(bill, accountId);
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable Long billId, @RequestBody Bill bill) {
        return billResponse.updateBill(billId, bill);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        return billResponse.deleteBill(billId);
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getBillsByCustomerId(@PathVariable Long customerId) {
        return billResponse.getBillsByCID(customerId);
    }
}