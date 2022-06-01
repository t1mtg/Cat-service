package ru.itmo.kotiki.service.service;

import ru.itmo.kotiki.dao.model.Color;
import ru.itmo.kotiki.service.dto.CatDto;

import java.util.List;
import java.util.UUID;

public interface CatService {

    public CatDto findCat(UUID id);

    public List<CatDto> findCatByColor(Color color);

    public CatDto saveCat(CatDto cat);

    public void deleteCat(UUID catId);

    public List<CatDto> getAllCats();
}
