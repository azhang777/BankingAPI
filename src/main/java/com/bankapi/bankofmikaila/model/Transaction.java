package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.enumeration.TransactionMedium;
import com.bankapi.bankofmikaila.enumeration.TransactionStatus;
import com.bankapi.bankofmikaila.enumeration.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.naming.Name;
import javax.persistence.*;

@Entity
@Table(name = "transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;  // P2P, DEPOSIT, WITHDRAWAL
    private String transactionDate;
    private TransactionStatus status;
    private TransactionMedium medium;
    private Double amount;
    private String description;
    @ManyToOne
    @JoinColumn(name = "account1_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "account2_id")

    private Account account2;

    public Transaction(){

    }

    public Transaction(Long id, TransactionType type, String transactionDate, TransactionStatus status, TransactionMedium medium, Double amount, String description, Account account) {
        this.id = id;
        this.type = type;
        this.transactionDate = transactionDate;
        this.status = status;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
        this.account = account;
    }

    @PrePersist
    public void prePersist() {
        // Set default values before persisting the entity
        if (status == null) {
            status = TransactionStatus.PENDING;
        }
        // You can set default values for other fields if needed
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionMedium getMedium() {
        return medium;
    }

    public void setMedium(TransactionMedium medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }
}
