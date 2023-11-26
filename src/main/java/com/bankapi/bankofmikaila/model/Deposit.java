package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.bankapi.bankofmikaila.dto.TransactionStatus;
import com.bankapi.bankofmikaila.dto.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction{

    public Deposit() {
    }

    public Deposit(Long id, TransactionType type, String transactionDate, TransactionStatus status, TransactionMedium medium, Double amount, String description, Account account) {
        super(id, type, transactionDate, status, medium, amount, description, account);
    }
}
