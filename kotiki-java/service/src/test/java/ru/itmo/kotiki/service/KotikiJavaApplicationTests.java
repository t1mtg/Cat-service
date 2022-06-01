package ru.itmo.kotiki.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itmo.kotiki.dao.model.Cat;
import ru.itmo.kotiki.dao.model.Color;
import ru.itmo.kotiki.dao.model.Owner;
import ru.itmo.kotiki.dao.repository.CatRepository;
import ru.itmo.kotiki.dao.repository.OwnerRepository;
import ru.itmo.kotiki.service.dto.CatDto;
import ru.itmo.kotiki.service.dto.OwnerDto;
import ru.itmo.kotiki.service.service.CatServiceImpl;
import ru.itmo.kotiki.service.service.OwnerServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class KotikiJavaApplicationTests {
    @Mock
    private CatRepository catRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerServiceImpl ownerServiceImpl;
    @InjectMocks
    private CatServiceImpl catServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCat() {
        Owner firstOwner = new Owner("t1mtg", new Date(792430437));
        String username = "t1mtg";
        String password = "Khabarovsk";
        Cat firstCat = new Cat("floppa", new Date(228228), "russian", Color.WHITE);
        CatDto firstCatDto = new CatDto(firstCat.getName(), firstCat.getBirthDate(), firstCat.getBreed(), firstCat.getColor(), firstCat.getOwner());
        UUID id = firstOwner.getId();
        firstCat.setOwner(firstOwner);
        ownerServiceImpl.saveOwner(new OwnerDto(firstOwner.getName(), firstOwner.getBirthDate(), username, password, firstOwner.getCats()));
        catServiceImpl.saveCat(firstCatDto);
        when(catRepository.findById(id)).thenReturn(java.util.Optional.of(firstCat));
        assertEquals(catServiceImpl.findCat(id).getOwner().getName(), "t1mtg");
    }

    @Test
    public void getOwner() {
        Owner firstOwner = new Owner("t1mtg", new Date(792430437));
        String firstUsername = "t1mtg";
        String firstPassword = "Khabarovsk";
        Owner secondOwner = new Owner("vano", new Date(792433217));
        String secondUsername = "vanya";
        String secondPassword = "lo66vv";
        Owner thirdOwner = new Owner("vova", new Date(792043327));
        String thirdUsername = "vladimir";
        String thirdPassword = "Knyaz";
        ownerServiceImpl.saveOwner(new OwnerDto(firstOwner.getName(), firstOwner.getBirthDate(), firstUsername, firstPassword, firstOwner.getCats()));
        ownerServiceImpl.saveOwner(new OwnerDto(secondOwner.getName(), secondOwner.getBirthDate(), secondUsername, secondPassword, secondOwner.getCats()));
        ownerServiceImpl.saveOwner(new OwnerDto(thirdOwner.getName(), thirdOwner.getBirthDate(), thirdUsername, thirdPassword, thirdOwner.getCats()));
        when(ownerRepository.findById(secondOwner.getId())).thenReturn(java.util.Optional.of(secondOwner));
        assertEquals(ownerServiceImpl.findOwner(secondOwner.getId()).getName(), "vano");
    }

    @Test
    public void getAllOwners() {
        Owner firstOwner = new Owner("t1mtg", new Date(792430437));
        String firstUsername = "t1mtg";
        String firstPassword = "Khabarovsk";
        Owner secondOwner = new Owner("vano", new Date(792433217));
        String secondUsername = "vanya";
        String secondPassword = "lo66vv";
        Owner thirdOwner = new Owner("vova", new Date(792043327));
        String thirdUsername = "vladimir";
        String thirdPassword = "Knyaz";
        OwnerDto firstOwnerDto = new OwnerDto(firstOwner.getName(), firstOwner.getBirthDate(), firstUsername, firstPassword, firstOwner.getCats());
        OwnerDto secondOwnerDto = new OwnerDto(secondOwner.getName(), secondOwner.getBirthDate(), secondUsername, secondPassword, secondOwner.getCats());
        OwnerDto thirdOwnerDto = new OwnerDto(thirdOwner.getName(), thirdOwner.getBirthDate(), thirdUsername, thirdPassword, thirdOwner.getCats());
        List<Owner> list = Stream.of(firstOwner, secondOwner, thirdOwner).collect(Collectors.toList());
        List<OwnerDto> listDto = Stream.of(firstOwnerDto, secondOwnerDto, thirdOwnerDto).collect(Collectors.toList());
        ownerServiceImpl.saveOwner(firstOwnerDto);
        ownerServiceImpl.saveOwner(secondOwnerDto);
        ownerServiceImpl.saveOwner(thirdOwnerDto);
        when(ownerRepository.findAll()).thenReturn(list);
        assertEquals(ownerServiceImpl.getAllOwners().size(), listDto.size());
    }


    @Test
    void contextLoads() {
    }

}
