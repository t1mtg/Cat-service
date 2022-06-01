package ru.itmo.kotiki.dao.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owners")

public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Cat> cats;

    public Owner(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        cats = new ArrayList<>();
    }

    public Owner() {
    }

    public void addCat(Cat cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", cats=" + cats +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
