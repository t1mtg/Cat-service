package ru.itmo.kotiki.service.dto;

import ru.itmo.kotiki.dao.model.Cat;
import ru.itmo.kotiki.dao.model.Role;
import ru.itmo.kotiki.dao.model.Status;

import java.sql.Date;
import java.util.List;

public class OwnerDto {
    private final String name;
    private final Date birthDate;
    private final String username;
    private final String password;
    private final Role role;
    private final Status status;
    private final List<Cat> cats;

    public OwnerDto(String name, Date birthDate, String username, String password, Role role, Status status, List<Cat> cats) {
        this.name = name;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.cats = cats;
    }

    public OwnerDto(String name, Date birthDate, String username, String password, List<Cat> cats) {
        this.name = name;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.cats = cats;
        this.role = Role.USER;
        this.status = Status.ENABLED;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }
}
