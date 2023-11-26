package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import com.bankapi.bankofmikaila.repository.WithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class P2PService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WithdrawRepo withdrawRepo;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private TransactionFactory transactionFactory;

    @Autowired
    private Transaction transaction;


    public



}
