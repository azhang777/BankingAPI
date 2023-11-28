package com.bankapi.bankofmikaila.controller;

import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p2p")
public class P2PController {
    @Autowired
    private TransactionResponse p2pResponse;

    @PostMapping("/accounts/{payerId}/{payeeId}")
    public ResponseEntity<?> createP2P(@PathVariable Long payerId, @PathVariable Long payeeId, @RequestBody Transaction p2p) {
        return new ResponseEntity<>(p2pResponse.createP2P(payerId, payeeId, p2p), HttpStatus.CREATED);
    }
}
