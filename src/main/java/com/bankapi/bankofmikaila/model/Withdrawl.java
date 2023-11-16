package com.bankapi.bankofmikaila.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "withdrawls")
public class Withdrawl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPOSIT_ID")
    private Long id;

    @Column(name="DEPOSIT_TYPE")
    @NotEmpty(message = "Need to input a deposit type")
    private String type;

    @Column(name="TRANSACTION_DATE")
    @NotEmpty(message = "Need to input a transaction date")
    private String transaction_date;

    @Column(name="STATUS")
    @NotEmpty(message = "Need to input a status")
    private String status;

    @Column(name="PAYEE_ID")
    @NotEmpty(message = "Need to input a payee id")
    private Long payee_id;


    @Column(name="MEDIUM")
    @NotEmpty(message = "Need to input a medium (balance/rewards)")
    private String medium;

    @Column(name="AMOUNT")
    @NotEmpty(message = "Need to input an amount")
    private Double amount;

    @Column(name="DESCRIPTION")
    @Value(" ")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


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

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
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