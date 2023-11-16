package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.body.Detail;
import com.bankapi.bankofmikaila.service.WithdrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class WithdrawlResponse
{
    @Autowired
    private WithdrawlService withdrawlService;


//    public ResponseEntity<?> getAllWithdrawls(Long accountId){
//        Detail detail = new Detail();
//        detail.setData(withdrawlService.getAllWithdrawlsByAID(accountId));
//        detail.setCode();
//    }

}
