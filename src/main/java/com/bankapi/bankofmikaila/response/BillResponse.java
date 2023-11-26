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
import java.util.stream.Collectors;

 /**
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

     public ResponseEntity<?> getAllBills(Long accountId) {
         List<Bill> bills = billService.getAllBills(accountId);
         Detail detail = new Detail();
         detail.setData(bills);
         detail.setCode(HttpStatus.OK.value());
         return new ResponseEntity<>(detail, HttpStatus.OK);
     }


     public ResponseEntity<?> getBillById(Long billId) {
         Optional<Bill> bill = billService.getBillById(billId);
         Detail detail = new Detail();
         if (bill.isPresent()) {
             detail.setData(bill.get());
             detail.setCode(HttpStatus.OK.value());
             return new ResponseEntity<>(detail, HttpStatus.OK);
         } else {
             detail.setMessage("Bill not found");
             return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
         }
     }

     public ResponseEntity<?> getBillsByCID(Long customerId) {
         List<Bill> bills = billService.getBillsByCID(customerId);
         Detail detail = new Detail();
         detail.setData(bills);
         detail.setCode(HttpStatus.OK.value());
         return new ResponseEntity<>(detail, HttpStatus.OK);
     }

     public ResponseEntity<?> createBill(Bill bill, Long accountId) {
         Detail detail = new Detail();
         Bill createdBill = billService.createBillForAccount(accountId, bill);
         detail.setData(createdBill);
         detail.setCode(HttpStatus.OK.value());
         detail.setMessage("Created bill and added it to the account");
         return new ResponseEntity<>(detail, HttpStatus.OK);
     }

     public ResponseEntity<?> updateBill(Long billId, Bill bill ){
     Detail detail = new Detail();
     detail.setMessage("Accepted bill modification");
     return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
     }


     public ResponseEntity<?> deleteBill(Long billId) {
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
 }

