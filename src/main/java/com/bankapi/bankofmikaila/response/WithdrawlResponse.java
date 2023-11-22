package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.exceptions.WithdrawalByIdNotFound;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.service.WithdrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class WithdrawlResponse
{
    @Autowired
    private WithdrawlService withdrawlService;




    public ResponseEntity<?> getAllWithdrawlsResponse(Long accountId){
        Detail detail = new Detail();
        detail.setData(withdrawlService.getAllWithdrawlsByAID(accountId));
        detail.setCode(HttpStatus.OK.value());


        return new ResponseEntity<>(detail, HttpStatus.OK);

    }

    public ResponseEntity<?> getWithdrawalByIdResponse(Long withdrawalId){
        Detail detail =new Detail();
        detail.setData(withdrawlService.getWithdrawlById(withdrawalId));
        detail.setCode(HttpStatus.OK.value());

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

@Transactional
    public ResponseEntity<?> createWithdrawal(Withdrawl withdrawl, Long accountId){

        Detail detail = new Detail();
        detail.setData(withdrawlService.createWithdrawal(accountId, withdrawl.getStatus(), withdrawl.getMedium(), withdrawl.getAmount(), withdrawl.getDescription()));
        detail.setCode(HttpStatus.OK.value());
        return  new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawl withdrawl) {
        Long withdrawalId = withdrawl.getId();  // Assuming Withdrawl has a getId() method

        Detail detail = new Detail();

        try {
            withdrawlService.updateWithdrawal(withdrawl, withdrawalId);
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
            withdrawlService.deleteWithdrawal(id);
            return ResponseEntity.ok("Withdrawal deleted successfully");
        } catch (WithdrawalByIdNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }





}
