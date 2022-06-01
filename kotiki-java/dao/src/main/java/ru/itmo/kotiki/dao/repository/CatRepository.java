package ru.itmo.kotiki.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.dao.model.Cat;
import ru.itmo.kotiki.dao.model.Color;
import ru.itmo.kotiki.dao.model.Owner;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatRepository extends JpaRepository<Cat, UUID> {
    List<Cat> findByColor(Color color);
    List<Cat> findByColorAndOwner(Color color, Owner owner);
    List<Cat> findByOwner(Owner owner);
}