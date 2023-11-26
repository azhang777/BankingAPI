package com.bankapi.bankofmikaila.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "withdrawals")
public class Withdrawl extends Transaction{


}
