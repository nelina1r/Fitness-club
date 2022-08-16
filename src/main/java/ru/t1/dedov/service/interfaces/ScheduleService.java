package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDataException;
import ru.t1.dedov.exceptions.InvalidRoleException;

import java.util.List;

public interface ScheduleService {
    List<ScheduleOutputDto> findAll();

    ScheduleOutputDto findById(Long id);

    ScheduleOutputDto save(ScheduleCreationDto scheduleDto) throws InvalidDataException, InvalidRoleException, InvalidCapacityException;

    void editById(Long id, ScheduleCreationDto scheduleDto);
    void deleteById(Long id);
}
