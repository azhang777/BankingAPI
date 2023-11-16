package com.bankapi.bankofmikaila.model;

import org.apache.tomcat.jni.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @Column(name = "customer id")
    public Long id;
    @Column(name = "First name")
    public String firstName;
    @Column(name = "Last name")
    public String lastName;
    @Column(name = "Address")
    public Set<Address> address;

    public Customer(Long id, String firstName, String lastName, Set address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}



