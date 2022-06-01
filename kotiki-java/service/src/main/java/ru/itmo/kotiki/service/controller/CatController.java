package ru.itmo.kotiki.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.dao.model.Color;
import ru.itmo.kotiki.service.dto.CatDto;
import ru.itmo.kotiki.service.service.CatService;
import ru.itmo.kotiki.service.service.CatServiceImpl;

import java.util.List;
import java.util.UUID;

@RequestMapping("/cat")
@RestController
public class CatController {
    private final CatService catServiceImpl;

    public CatController(CatServiceImpl catServiceImpl) {
        this.catServiceImpl = catServiceImpl;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user')")
    public HttpStatus createCat(@RequestBody CatDto catDto) {
        catServiceImpl.saveCat(catDto);
        return HttpStatus.CREATED;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user')")
    public List<CatDto> getAllCats() {
        return catServiceImpl.getAllCats();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    public CatDto getCat(@PathVariable UUID id) {
        return catServiceImpl.findCat(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public HttpStatus deleteCat(@PathVariable UUID id) {
        catServiceImpl.deleteCat(id);
        return HttpStatus.OK;
    }

    @GetMapping("byColor/{color}")
    @PreAuthorize("hasAuthority('user')")
    public List<CatDto> getCatsByColor(@PathVariable Color color) {
        return catServiceImpl.findCatByColor(color);
    }

}
