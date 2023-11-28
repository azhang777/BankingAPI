package com.bankapi.bankofmikaila.model;

import javax.persistence.*;


    // Primary key for the Bill entity
@Entity
@Table(name= "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;


    // Various properties of a bill
    private String status;

    private String payee;

    private String nickname;

    private String creationDate;

    private String paymentDate;

    private Integer recurringDate;

    private String upcomingPaymentDate;

    private Double paymentAmount;

    // Many-to-One relationship with Account entity
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Many-to-One relationship with Customer entity
   @JoinColumn(name = "customer_id")
   @ManyToOne
   private Customer customer;

        public Long getId () {
        return id;
        }
        public void setId (Long id){
            this.id = id;
        }

        public String getStatus () {
            return status;
        }

        public void setStatus (String status){
            this.status = status;
        }

        public String getPayee () {
            return payee;
        }

        public void setPayee (String payee){
            this.payee = payee;
        }

        public String getNickname () {
            return nickname;
        }

        public void setNickname (String nickname){
            this.nickname = nickname;
        }

        public String getCreationDate () {
            return creationDate;
        }

        public void setCreationDate (String creationDate){
            this.creationDate = creationDate;
        }

        public String getPaymentDate () {
            return paymentDate;
        }

        public void setPaymentDate (String paymentDate){
            this.paymentDate = paymentDate;
        }

        public Integer getRecurringDate () {
            return recurringDate;
        }

        public void setRecurringDate (Integer recurringDate){
            this.recurringDate = recurringDate;
        }

        public String getUpcomingPaymentDate () {
            return upcomingPaymentDate;
        }

        public void setUpcomingPaymentDate (String upcomingPaymentDate){
            this.upcomingPaymentDate = upcomingPaymentDate;
        }

        public Double getPaymentAmount () {
            return paymentAmount;
        }

        public void setPaymentAmount (Double paymentAmount){
            this.paymentAmount = paymentAmount;
        }

        public Account getAccount () {
            return account;
        }

        public void setAccount (Account account){
            this.account = account;
        }

        public Customer getCustomer() {
        return customer;
    }

        public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    }


