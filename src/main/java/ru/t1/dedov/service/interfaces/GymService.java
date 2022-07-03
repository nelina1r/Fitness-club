package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.GymDto;

import java.util.List;

public interface GymService {

    List<GymDto> findAll();

    GymDto findById(Long id);

    GymDto save(GymDto gymDto);

    void deleteById(Long id);
}
