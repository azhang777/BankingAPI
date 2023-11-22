package com.bankapi.bankofmikaila.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("P2P")
public class P2P extends Transaction{

}
