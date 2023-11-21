package com.bankapi.bankofmikaila.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity

@DiscriminatorValue("WITHDRAWAL")
public class Withdrawl extends Transaction{





}
