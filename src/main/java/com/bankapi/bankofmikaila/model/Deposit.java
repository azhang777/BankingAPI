package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.enumeration.TransactionMedium;
import com.bankapi.bankofmikaila.enumeration.TransactionStatus;
import com.bankapi.bankofmikaila.enumeration.TransactionType;

import javax.persistence.*;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction{

    public Deposit() {
    }

    public Deposit(Long id, TransactionType type, String transactionDate, TransactionStatus status, TransactionMedium medium, Double amount, String description, Account account) {
        super(id, type, transactionDate, status, medium, amount, description, account);
    }
}
