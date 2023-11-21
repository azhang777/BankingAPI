package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.dto.TransactionMedium;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Deposit")
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction{


}
