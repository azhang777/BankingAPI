package com.bankapi.bankofmikaila.controller;


import com.bankapi.bankofmikaila.model.P2P;
import com.bankapi.bankofmikaila.service.P2PService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p2p")
public class P2PController {
    @Autowired
    private P2PService p2pService;

    @PostMapping("/accounts/{payerId}/{payeeId}")
    public ResponseEntity<?> createP2P(@PathVariable Long payerId, @PathVariable Long payeeId, @RequestBody P2P p2p) {
        return new ResponseEntity<>(p2pService.createP2P(payerId, payeeId, p2p), HttpStatus.CREATED);
    }
}
