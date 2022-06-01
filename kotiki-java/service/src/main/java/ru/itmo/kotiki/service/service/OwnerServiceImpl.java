package ru.itmo.kotiki.service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.model.Owner;
import ru.itmo.kotiki.dao.model.User;
import ru.itmo.kotiki.dao.repository.OwnerRepository;
import ru.itmo.kotiki.dao.repository.UserRepository;
import ru.itmo.kotiki.service.dto.OwnerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }

    public OwnerDto findOwner(UUID id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.map(value -> new OwnerDto(value.getName(),
                value.getBirthDate(), value.getUser().getUsername(), value.getUser().getPassword(), value.getCats())).orElse(null);
    }

    public OwnerDto saveOwner(OwnerDto owner) {
        Owner newOwner = new Owner(owner.getName(), owner.getBirthDate());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User(owner.getUsername(), passwordEncoder.encode(owner.getPassword()), owner.getRole(), owner.getStatus());
        newUser.setOwner(newOwner);
        newOwner.setUser(newUser);
        ownerRepository.save(newOwner);
        userRepository.save(newUser);
        return owner;
    }

    public void deleteOwner(UUID ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(owner -> new OwnerDto(owner.getName(), owner.getBirthDate(), owner.getUser().getUsername(),
                        owner.getUser().getPassword(), owner.getCats()))
                .collect(Collectors.toList());
    }
}

