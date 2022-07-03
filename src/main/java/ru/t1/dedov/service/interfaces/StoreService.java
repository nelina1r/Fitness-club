package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.dto.StoreDto;

import java.util.List;

public interface StoreService {

    List<StoreDto> findAll();

    StoreDto findById(Long id);

    StoreDto save(StoreDto storeDto);

    void deleteById(Long id);
}
