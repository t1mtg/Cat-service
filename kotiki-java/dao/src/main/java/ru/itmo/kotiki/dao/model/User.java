package ru.itmo.kotiki.dao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Owner owner;

    public User(String username, String password, Role role, Status status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User() {
    }


    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", owner=" + owner +
                '}';
    }
}
