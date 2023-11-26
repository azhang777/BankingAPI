package com.bankapi.bankofmikaila.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("WITHDRAWAL")
public class Withdrawal extends Transaction{
    public Withdrawal() {
    }

}
