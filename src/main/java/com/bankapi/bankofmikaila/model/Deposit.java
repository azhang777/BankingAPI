package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.dto.TransactionMedium;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPOSIT_ID")
    private Long id;

    @Column(name="DEPOSIT_TYPE")
    private String type;

    @Column(name="TRANSACTION_DATE")
    private String transaction_date;

    @Column(name="STATUS")
    private String status;

    @Column(name="PAYEE_ID")
    private Long payee_id;

    @Column(name="MEDIUM")
    private String medium;

    @Column(name="AMOUNT")
    private Double amount;

    @Column(name="DESCRIPTION")
    private String description;








}
