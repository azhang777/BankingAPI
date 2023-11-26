package com.bankapi.bankofmikaila.service;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.bankapi.bankofmikaila.dto.TransactionStatus;
import com.bankapi.bankofmikaila.dto.TransactionType;

import com.bankapi.bankofmikaila.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionFactory {

    public static Transaction createTransaction(Account account, TransactionType type,
                                                TransactionStatus status, TransactionMedium medium,
                                                Double amount, String description) {
        Transaction transaction;

        switch (type) {
            case P2P:
                transaction = createP2PTransaction(account, status, medium, amount, description);
                break;
            case DEPOSIT:
                transaction = createDepositTransaction(account, status, medium, amount, description);
                break;
            case WITHDRAWAL:
                transaction = createWithdrawalTransaction(account, status, medium, amount, description);
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }

        return transaction;
    }

    private static P2P createP2PTransaction(Account account,
                                                       TransactionStatus status, TransactionMedium medium,
                                                       Double amount, String description) {
        P2P p2pTransaction = new P2P();
        setCommonFields(p2pTransaction, account, TransactionType.P2P, status, medium, amount, description);
        return p2pTransaction;
    }

    private static Deposit createDepositTransaction(Account account,
                                                    TransactionStatus status, TransactionMedium medium,
                                                    Double amount, String description) {
        Deposit deposit = new Deposit();
        setCommonFields(deposit, account, TransactionType.DEPOSIT, status, medium, amount, description);
        return deposit;
    }

    private static Withdrawal createWithdrawalTransaction(Account account,
                                                          TransactionStatus status, TransactionMedium medium,
                                                          Double amount, String description) {
        Withdrawal withdrawal = new Withdrawal();
        setCommonFields(withdrawal, account, TransactionType.WITHDRAWAL, status, medium, amount, description);
        return withdrawal;
    }

    private static void setCommonFields(Transaction transaction, Account account,
                                        TransactionType type, TransactionStatus status,
                                        TransactionMedium medium, Double amount, String description) {
        transaction.setType(type);
        transaction.setAccount(account);
        transaction.setTransactionDate(LocalDateTime.now().toString());
        transaction.setStatus(status);
        transaction.setMedium(medium);
        transaction.setAmount(amount);
        transaction.setDescription(description);
    }
}