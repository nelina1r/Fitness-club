package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.exceptions.InvalidDataException;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> findAll();

    ScheduleDto findById(Long id);

    ScheduleDto save(ScheduleDto scheduleDto) throws InvalidDataException;

    void deleteById(Long id);
}
