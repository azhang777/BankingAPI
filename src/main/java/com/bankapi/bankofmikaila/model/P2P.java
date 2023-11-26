package com.bankapi.bankofmikaila.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@DiscriminatorValue("P2P")
public class P2P extends Transaction{

    @ManyToOne
    @JoinColumn(name = "p2p_account_id", columnDefinition = "VARCHAR(255) DEFAULT 'NA'")
    private Account account2;

    @Transient
    @OneToOne
    @JsonIgnore
    private Withdrawal withdrawl;

    @Transient
    @OneToOne
    @JsonIgnore
    private Deposit deposit;

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    public Withdrawal getWithdrawl() {
        return withdrawl;
    }

    public void setWithdrawl(Withdrawal withdrawl) {
        this.withdrawl = withdrawl;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }
}
