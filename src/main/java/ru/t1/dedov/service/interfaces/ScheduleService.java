package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> findAll();

    ScheduleDto findById(Long id);

    ScheduleDto save(ScheduleDto scheduleDto);

    void deleteById(Long id);
}
