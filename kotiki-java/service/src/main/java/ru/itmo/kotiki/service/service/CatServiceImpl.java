package ru.itmo.kotiki.service.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.model.*;
import ru.itmo.kotiki.dao.repository.CatRepository;
import ru.itmo.kotiki.dao.repository.UserRepository;
import ru.itmo.kotiki.service.dto.CatDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final UserRepository userRepository;

    public CatServiceImpl(CatRepository catRepository, UserRepository userRepository) {
        this.catRepository = catRepository;
        this.userRepository = userRepository;
    }

    public CatDto findCat(UUID id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Optional<Cat> cat = catRepository.findById(id);
        return cat
                .filter(c -> c.getOwner().getUser().getId().equals(userRepository.findByUsername(username).get().getId())
                        || userRepository.findByUsername(username).get().getRole().equals(Role.ADMIN))
                .map(value -> new CatDto(value.getName(),
                        value.getBirthDate(),
                        value.getBreed(),
                        value.getColor(),
                        value.getOwner())).orElse(null);
    }

    public List<CatDto> findCatByColor(Color color) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if (userRepository.findByUsername(username).get().getRole().equals(Role.ADMIN)) {
            List<Cat> cats = catRepository.findByColor(color);
            return cats.stream().map(value -> new CatDto(value.getName(),
                    value.getBirthDate(),
                    value.getBreed(),
                    value.getColor(),
                    value.getOwner()))
                    .collect(Collectors.toList());
        }

        Owner owner = userRepository.findByUsername(username).get().getOwner();
        List<Cat> cats = catRepository.findByColorAndOwner(color, owner);
        return cats.stream()
                .map(value -> new CatDto(value.getName(),
                        value.getBirthDate(),
                        value.getBreed(),
                        value.getColor(),
                        value.getOwner()))
                .collect(Collectors.toList());
    }


    public CatDto saveCat(CatDto cat) {
        Cat new_cat = new Cat(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
        catRepository.save(new_cat);
        return cat;
    }

    public void deleteCat(UUID catId) {
        catRepository.deleteById(catId);
    }

    public List<CatDto> getAllCats() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Owner owner = userRepository.findByUsername(username).get().getOwner();
        if (userRepository.findByUsername(username).get().getRole().equals(Role.ADMIN)) {
            List<Cat> cats = catRepository.findAll();
            return cats.stream().map(value -> new CatDto(value.getName(),
                    value.getBirthDate(),
                    value.getBreed(),
                    value.getColor(),
                    value.getOwner()))
                    .collect(Collectors.toList());
        }

        return catRepository.findByOwner(owner)
                .stream()
                .map(cat -> new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), cat.getOwner()))
                .collect(Collectors.toList());
    }
}

