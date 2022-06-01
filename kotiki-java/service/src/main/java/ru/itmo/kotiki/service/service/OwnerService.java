package ru.itmo.kotiki.service.service;

import ru.itmo.kotiki.service.dto.OwnerDto;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    OwnerDto findOwner(UUID id);

    OwnerDto saveOwner(OwnerDto owner);

    void deleteOwner(UUID ownerId);

    List<OwnerDto> getAllOwners();
}
