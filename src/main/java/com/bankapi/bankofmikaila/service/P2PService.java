package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.bankapi.bankofmikaila.dto.TransactionStatus;
import com.bankapi.bankofmikaila.dto.TransactionType;
import com.bankapi.bankofmikaila.model.*;
import com.bankapi.bankofmikaila.repository.AccountRepository;
import com.bankapi.bankofmikaila.repository.DepositRepository;
import com.bankapi.bankofmikaila.repository.TransactionRepository;
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
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionFactory transactionFactory;

    @Autowired
    private WithdrawalService withdrawalService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private AccountService accountService;

    public P2P createP2P(Long accountId, P2P p2p) {
        Account payer = accountService.getAccountById(accountId);
        Account payee = accountService.getAccountById(p2p.getAccount2().getId());

        Withdrawal withdrawl = new Withdrawal();
        Deposit deposit = new Deposit();

        withdrawl.setAccount(payer);
        withdrawl.setAmount(p2p.getAmount());
        withdrawl.setDescription(p2p.getDescription());
        withdrawl.setType(TransactionType.WITHDRAWAL);
        withdrawl.setMedium(TransactionMedium.BALANCE);
        withdrawl.setStatus(TransactionStatus.PENDING);
        withdrawl.setTransactionDate(p2p.getTransactionDate());
        p2p.setWithdrawl(withdrawl);

        deposit.setAccount(payee);
        deposit.setAmount(p2p.getAmount());
        deposit.setDescription(p2p.getDescription());
        deposit.setType(TransactionType.WITHDRAWAL);
        deposit.setMedium(TransactionMedium.BALANCE);
        deposit.setStatus(TransactionStatus.PENDING);
        deposit.setTransactionDate(p2p.getTransactionDate());
        //p2p.setDeposit(deposit);

        withdrawalService.createWithdrawl(withdrawl, payee.getId());
        depositService.createDeposit(deposit, payer.getId());

        return transactionRepository.save(p2p);
    }





}
