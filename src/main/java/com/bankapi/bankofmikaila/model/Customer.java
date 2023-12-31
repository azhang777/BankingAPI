package com.bankapi.bankofmikaila.model;


/*
 * @IMPORTANT - Needs @Validation for everything @NotNull / @NotEmpty
 */
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
    private Long id;
    private String firstName;
    private String lastName;

    /**
     * @OneToMany(mappedBy=customer) - This annotation is used to define a one-to-many relationship between entities.
     * In the context of your code snippet, it's part of the Customer entity class,
     * and it indicates that one Customer can have multiple Address entities associated with it.
     *
     * mappedBy = "customer": This attribute specifies the field in the Address entity that owns the relationship.
     * In this case, the Address entity should have a field named customer that maps back to the owning side of the relationship.
     * This helps avoid the creation of an additional database join table for the relationship.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Set <Address> address;





    //to remove
    public Customer(){

    }

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

    @Override
    public String toString() {
        return "Customer {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}



