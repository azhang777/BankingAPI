package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.model.Withdrawl;
import com.bankapi.bankofmikaila.service.WithdrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


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


    public ResponseEntity<?> createWithdrawal(Withdrawl withdrawl, Long accountId){
        accountId = withdrawl.getAccount().getId();
        Detail detail = new Detail();
        detail.setData(withdrawlService.createWithdrawl(withdrawl, accountId ));
        detail.setCode(HttpStatus.OK.value());
        return  new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawl withdrawl, Long withdrawalId)

        {
            withdrawalId = withdrawl.getId();

        Detail detail = new Detail();
        withdrawlService.updateWithdrawl(withdrawl, withdrawalId);
        detail.setMessage("Accepted withdrawal modification");
        detail.setCode(HttpStatus.ACCEPTED.value());

        return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);

    }


    public ResponseEntity<?> deleteWithdrawal(Withdrawl withdrawl){
    withdrawlService.deleteWithdrawals(withdrawl.getId());
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }




}
