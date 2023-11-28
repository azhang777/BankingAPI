package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.exception.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.model.Withdrawal;
import com.bankapi.bankofmikaila.service.TransactionFactory;
import com.bankapi.bankofmikaila.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionResponse {

    @Autowired
    private TransactionService transactionService;


    public ResponseEntity<?> getAllWithdrawlsResponse(Long accountId){
        Detail detail = new Detail();
        detail.setData(transactionService.getAllWithdrawlsByAID(accountId));
        detail.setCode(HttpStatus.OK.value());


        return new ResponseEntity<>(detail, HttpStatus.OK);

    }

    public ResponseEntity<?> getWithdrawalByIdResponse(Long withdrawalId){
        Detail detail =new Detail();
        detail.setData(transactionService.getWithdrawlById(withdrawalId));
        detail.setCode(HttpStatus.OK.value());

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawl, Long accountId){

        Detail detail = new Detail();
        detail.setData( transactionService.createWithdrawl(withdrawl, accountId));
        detail.setCode(HttpStatus.OK.value());
        return  new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawl, Long withdrawalId ) {
        withdrawalId = withdrawl.getId();  // Assuming Withdrawl has a getId() method

        Detail detail = new Detail();

        try {
            transactionService.updateWithdrawl(withdrawl, withdrawalId);
            detail.setMessage("Accepted withdrawal modification");
            detail.setCode(HttpStatus.ACCEPTED.value());
            return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
        } catch (WithdrawalByIdNotFound e) {
            detail.setMessage("Withdrawal ID not found");
            detail.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> deleteWithdrawal(Long id) {
        try {
            transactionService.deleteWithdrawal(id);
            return ResponseEntity.ok("Withdrawal deleted successfully");
        } catch (WithdrawalByIdNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
