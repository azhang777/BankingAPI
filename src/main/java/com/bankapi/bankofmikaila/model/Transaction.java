package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.bankapi.bankofmikaila.dto.TransactionStatus;
import com.bankapi.bankofmikaila.dto.TransactionType;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type",  insertable = false, updatable = false)
    private TransactionType type;  // P2P, DEPOSIT, WITHDRAWAL

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "medium")
    private TransactionMedium medium;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "transaction_account_id")
    private Account account;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
}
