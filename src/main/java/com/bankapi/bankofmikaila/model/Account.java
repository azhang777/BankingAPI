package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.dto.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;


@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;
    @Column(name = "type")
//    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "rewards")
    @Value("0")
    private Integer rewards;
    @Column(name = "balance")
    private Double balance;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")// This is the join column in the Account table
    private Customer customer;
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customer_id;
    public Account() {
    }

    public Account(Long id, AccountType type, String nickname, Integer rewards, Double balance, Customer customer) {
        this.id = id;
        this.type = type;
        this.nickname = nickname;
        this.rewards = rewards;
        this.balance = balance;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            this.customer_id = customer.getId();
        }
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }
}
