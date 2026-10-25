package com.bankapi.bankofmikaila.model;

import com.bankapi.bankofmikaila.enumeration.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;   // BCrypt hash only, never plain text

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public AppUser() {
    }


    public AppUser(String username ,Role role, String password) {
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public AppUser(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
