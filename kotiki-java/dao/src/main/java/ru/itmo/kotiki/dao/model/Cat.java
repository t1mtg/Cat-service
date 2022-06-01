package ru.itmo.kotiki.dao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "breed")
    private String breed;

    @Column(name = "color")
    private Color color;

    public Cat() {
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

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    public Cat(String name, Date birthDate, String breed, Color color) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

}
