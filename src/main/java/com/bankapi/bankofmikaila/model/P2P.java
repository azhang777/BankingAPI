package com.bankapi.bankofmikaila.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="P2P")
@DiscriminatorValue("P2P")
public class P2P extends Transaction{

}
