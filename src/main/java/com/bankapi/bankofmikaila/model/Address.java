package com.bankapi.bankofmikaila.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    public Long id;
    @Column(name = "street_number")
    public String street_number;
    @Column(name = "street_name")
    public String street_name;
    @Column(name = "city")
    public String city;
    @Column(name = "state")
    public String state;
    @Column(name = "zip")
    public String zip;

    /**
     * @ManyToOne - This annotation is used to define a many-to-one relationship between entities.
     * It indicates that many instances of the annotated class (in this case, Address) can be associated
     * with a single instance of another class (in this case, Customer).
     *
     *
     * @JoinColumn - This annotation is used to specify the foreign key column in the Address table that is used
     * to join with the primary key column of the Customer table.
     * In this case, the foreign key column is named customer_id.
     *
     *
     *
     * mappedBy = "customer": This attribute specifies the field in the Address entity that owns the relationship. In this case,
     * the Address entity should have a field named customer that maps back to the owning side of the relationship.
     * This helps avoid the creation of an additional database join table for the relationship.
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street_number='" + street_number + '\'' +
                ", street_name='" + street_name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'';
    }
}
