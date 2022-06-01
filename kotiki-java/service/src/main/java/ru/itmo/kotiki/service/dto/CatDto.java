package ru.itmo.kotiki.service.dto;

import ru.itmo.kotiki.dao.model.Color;
import ru.itmo.kotiki.dao.model.Owner;

import java.sql.Date;

public class CatDto {
    private final String name;
    private final Date birthDate;
    private final String breed;
    private final Color color;
    private final Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public CatDto(String name, Date birthDate, String breed, Color color, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
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
}
