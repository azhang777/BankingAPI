package com.bankapi.bankofmikaila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity

@DiscriminatorValue("WITHDRAWAL")

public class Withdrawl extends Transaction{



}
