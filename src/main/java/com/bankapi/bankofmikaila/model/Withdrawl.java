package com.bankapi.bankofmikaila.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "withdrawals")
public class Withdrawl {

    @Id
    @GeneratedValue
    @Column(name="WITHDRAWAL_ID")
    private Long id;

    @Column(name="WITHDRAWAL_TYPE")
    @NotEmpty(message = "Need to input a withdrawal type")
    private String type;

    @Column(name="TRANSACTION_DATE")
    @NotEmpty(message = "Need to input a transaction date")
    private String transaction_date;

    @Column(name="STATUS")
    @NotEmpty(message = "Need to input a status")
    private String status;



    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account account;


    @Column(name="MEDIUM")
    @NotEmpty(message = "Need to input a medium (balance/rewards)")
    private String medium;

    @Column(name="AMOUNT")
    @NotNull(message = "Amount must not be null")
    private Double amount;

    @Column(name="DESCRIPTION")
    @Value(" ")
    private String description;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
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
