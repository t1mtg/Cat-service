package ru.itmo.kotiki.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.service.dto.OwnerDto;
import ru.itmo.kotiki.service.service.OwnerService;
import ru.itmo.kotiki.service.service.OwnerServiceImpl;

import java.util.List;
import java.util.UUID;

@RequestMapping("/owner")
@RestController
public class OwnerController {
    private final OwnerService ownerServiceImpl;

    public OwnerController(OwnerServiceImpl ownerServiceImpl) {

        this.ownerServiceImpl = ownerServiceImpl;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public HttpStatus createOwner(@RequestBody OwnerDto ownerDto) {
        ownerServiceImpl.saveOwner(ownerDto);
        return HttpStatus.CREATED;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public List<OwnerDto> getAllOwners() {
        return ownerServiceImpl.getAllOwners();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public OwnerDto getOwner(@PathVariable UUID id) {
        return ownerServiceImpl.findOwner(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public HttpStatus deleteOwner(@PathVariable UUID id) {
        ownerServiceImpl.deleteOwner(id);
        return HttpStatus.OK;
    }
}
