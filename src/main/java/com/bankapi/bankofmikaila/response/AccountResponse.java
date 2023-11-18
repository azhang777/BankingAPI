package com.bankapi.bankofmikaila.response;

import com.bankapi.bankofmikaila.dto.Detail;
import com.bankapi.bankofmikaila.model.Account;
import com.bankapi.bankofmikaila.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountResponse {

    @Autowired
    private AccountService accountService;

    public ResponseEntity<?> getAllAccounts() {
        Detail detail = new Detail();
        detail.setData(accountService.getAllAccounts());
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - All accounts retrieved.");

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> getAccountById(Long accountId) {
        Detail detail = new Detail();
        detail.setData(accountService.getAccountById(accountId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - Account " + accountId + " retrieved.");

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> updateAccount(Long accountId, Account updatedAccount) {
        Detail detail = new Detail();
        accountService.updateAccount(accountId,updatedAccount);
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Success - Account " + accountId + " updated.");

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAccount(Long accountId) {
        Detail detail = new Detail();
        accountService.deleteAccount(accountId);
        detail.setCode(HttpStatus.ACCEPTED.value());
        detail.setMessage("Success - Account " + accountId + " deleted.");

        return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
    }


    /*
    GET BODY
    {
        “code” : 200,
        “message”: “Success”,
        “data”:
        [
            {
                "id": 1,
                "type": "Savings",
                "nickname": "Leon’s Account",
                "rewards": 37605,
                "balance": 19351,
                "customer_id": 10
            },
            {
                "id": 2,
                "type": "Savings",
                "nickname": "Zach Account",
                "rewards": 37605,
                "balance": 19351,
                "customer_id": 11
            }
            ]
        }
     */

    /*
    POST BODY
    {
        "code": 201,
        "message": "Account created",
        "data": {
            "type": "Credit Card",
            "nickname": "string",
            "rewards": 0,
            "balance": 0,
            "customer_id": 10,
            "_id": 42
        }
    }
     */
    /*
     UPDATE BODY
     {
        "code": 200,
        "message": "Customer account updated"
     }
     */

    /*
    DELETE BODY
    {
        "code": 202,
        "message": "Account successfully deleted"
    }
     */



}
