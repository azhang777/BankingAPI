package com.bankapi.bankofmikaila.response;



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
import java.util.stream.Collectors;

 /**
     * @TODO - Autowire the bill service
     *
     * @Autowired
     * private BillService billService;
     *
     * @Todo - create 6 methods that all return a response entity for each method in the bill service
     *
     * @Method1
     *
     * public ResponseEntity<?> getAllBills(Long accountId){
     *     Detail detail = new Detail();
     *     detail.setData(billService.getAllBills(accountId);
     *     detail.setCode(HttpStatus.OK.value());
     *
     *     return new ResponseEntity<>(detail, HttpStatus.OK);
     * }
     *
     * @Method2
     * public ResponseEntity<?> getBillById(Long billId){
     *           Detail detail = new Detail();
     *           detail.setData(billService.getAllBills(billId);
     *          detail.setCode(HttpStatus.OK.value());
     *
     *           return new ResponseEntity<>(detail, HttpStatus.OK);
     *       }
     *
     *
     * @Method3
     *    public ResponseEntity<?> getBillsByCID(Long customerId){
     *                 Detail detail = new Detail();
     *                detail.setData(billService.getBillsByCID(billId);
     *               detail.setCode(HttpStatus.OK.value());
     *
     *                return new ResponseEntity<>(detail, HttpStatus.OK);
     *           }
     *
     *
     * @Method4
     *     public ResponseEntity<?> createBill(Bill bill, Long accountId){
     *                      Detail detail = new Detail();
     *                    detail.setData(billService.getBillsByCID(bill,accountId);
     *                   detail.setCode(HttpStatus.OK.value());
     *                   detail.setMessage("Created bill and added it to the account")
     *
     *                      return new ResponseEntity<>(detail, HttpStatus.OK);
     *                }
     *
     * @Method5
     *       public ResponseEntity<?> updateBill(Long billId, Bill bill ){
     *                           Detail detail = new Detail();
     *                       detail.setMessage("Accepted bill modification");
     *
     *                           return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
     *                    }
     * @Method6
     *  public ResponseEntity<?> deleteBill(Long billId){
     *
     *
     *             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     *
     *      }
     *
     */

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

