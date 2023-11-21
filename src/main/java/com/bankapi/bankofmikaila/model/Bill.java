package com.bankapi.bankofmikaila.model;

import javax.persistence.*;
import java.util.Objects;



/* @CLASS - Bill
 *
 * @Review - You need the following annotations
 * for line 40 through 48
 *
 *     @Column(name = "bill_id")
 *     @Column(name = "status")
 *     @Column(name = "payee")
 *     @Column(name = "nickname")
 *     @Column(name = "creationDate")
 *     @Column(name = "paymentDate")
 *     @Column(name = "recurringDate")
 *     @Column(name = "upcomingPaymentDate")
 *     @Column(name = "paymentAmount")
 *     @Column(name = "paymentAmount")
 *
 *
 * @Review - You need two annotations above 52
 * that looks like this
 *
 * @JoinColumn(name = "account_id")
 *
 * @ManyToOne
 */

@Entity
@Table(name= "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;


    @Column(name = "status")
    private String status;

    @Column(name = "payee")
    private String payee;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "creationDate")
    private String creationDate;

    @Column(name = "paymentDate")
    private String paymentDate;

    @Column(name = "recurringDate")
    private Integer recurringDate;

    @Column(name = "upcomingPaymentDate")
    private String upcomingPaymentDate;

    @Column(name = "paymentAmount")
    private Double paymentAmount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Bill() {
    }

    @Column(name = "status")
    private String status;
    @Column(name = "payee")
    private String payee;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "creationDate")
    private String creationDate;
    @Column(name = "paymentDate")
    private String paymentDate;
    @Column(name = "recurringDate")
    private Integer recurringDate;
    @Column(name = "upcomingPaymentDate")
    private String upcomingPaymentDate;
    @Column(name = "paymentAmount")
    private Double paymentAmount;
    @Column(name = "accountId")
    private Long accountId;


    public Bill(Long id, String status, String payee, String nickname, String creationDate, String paymentDate, Integer recurringDate, String upcomingPaymentDate, Double paymentAmount, Long accountId) {
        this.id = id;
        this.status = status;
        this.payee = payee;
        this.nickname = nickname;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.recurringDate = recurringDate;
        this.upcomingPaymentDate = upcomingPaymentDate;
        this.paymentAmount = paymentAmount;
        this.account = account;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getRecurringDate() {
        return recurringDate;
    }

    public void setRecurringDate(Integer recurringDate) {
        this.recurringDate = recurringDate;
    }

    public String getUpcomingPaymentDate() {
        return upcomingPaymentDate;
    }

    public void setUpcomingPaymentDate(String upcomingPaymentDate) {
        this.upcomingPaymentDate = upcomingPaymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", payee='" + payee + '\'' +
                ", nickname='" + nickname + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", recurringDate=" + recurringDate +
                ", upcomingPaymentDate='" + upcomingPaymentDate + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", account='" + account + '\'' +
                '}';
    }

    public enum BillStatus {
        PENDING,
        CANCELLED,
        COMPLETED,
        RECURRING
    }

}

