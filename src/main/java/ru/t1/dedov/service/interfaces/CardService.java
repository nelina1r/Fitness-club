package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.CardDto;

import java.util.List;

public interface CardService {

    List<CardDto> findAll();

    CardDto findById(Long id);

    CardDto save(CardDto cardDto);

    void deleteById(Long id);

}
