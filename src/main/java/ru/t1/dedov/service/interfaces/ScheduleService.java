package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDateTimeException;
import ru.t1.dedov.exceptions.InvalidRoleException;
import ru.t1.dedov.model.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<ScheduleOutputDto> findAll(Specification<Schedule> spec, String search, Pageable page);

    ScheduleOutputDto findById(Long id);

    ScheduleOutputDto save(Schedule schedule) throws InvalidDateTimeException, InvalidRoleException, InvalidCapacityException;

    Schedule parseSchedule(ScheduleCreationDto scheduleCreationDto);

    void editById(Long id, Schedule schedule) throws InvalidDateTimeException, InvalidCapacityException, InvalidRoleException;
    void deleteById(Long id);
}
