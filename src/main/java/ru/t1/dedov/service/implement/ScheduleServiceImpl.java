package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDateTimeException;
import ru.t1.dedov.exceptions.InvalidRoleException;
import ru.t1.dedov.filter.service.implement.ScheduleFilterService;
import ru.t1.dedov.mapper.ScheduleMapper;
import ru.t1.dedov.model.entity.*;
import ru.t1.dedov.model.repository.*;
import ru.t1.dedov.service.interfaces.ScheduleService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final EmployeeTrainingTypeRepository employeeTTRepository;
    private final GymRepository gymRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleFilterService scheduleFilterService;

    @Override
    public List<ScheduleOutputDto> findAll(String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return scheduleRepository.findAll(page)
                    .stream()
                    .map(scheduleMapper::toDto)
                    .collect(Collectors.toList());
        else
            return scheduleRepository.findAll(scheduleFilterService.generateSearchSpecifications(search), page)
                    .stream()
                    .map(scheduleMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Override
    public ScheduleOutputDto findById(Long id) {
        return scheduleMapper.toDto(scheduleRepository.findById(id).get());
    }

    @Override
    public Schedule parseSchedule(ScheduleCreationDto scheduleCreationDto) {
        Employee employee = employeeRepository.getReferenceById(scheduleCreationDto.getEmployeeId());
        EmployeeTrainingType employeeTrainingType = new EmployeeTrainingType();
        employeeTrainingType.setEmployee(employee);
        employeeTrainingType.setTrainingType(trainingTypeRepository.getReferenceById(scheduleCreationDto.getTrainingTypeId()));
        if (!employeeTTRepository.exists(Example.of(employeeTrainingType))) {
            employeeTTRepository.save(employeeTrainingType);
        }
        Gym gym = gymRepository.getReferenceById(scheduleCreationDto.getGymId());
        Schedule schedule = new Schedule();
        schedule.setGym(gym);
        schedule.setEmployeeTrainingType(employeeTrainingType);
        schedule.setTrainingStartDateTime(scheduleCreationDto.getTrainingStartDateTime());
        schedule.setTrainingDuration(scheduleCreationDto.getTrainingDuration());
        schedule.setPeopleCapacity(scheduleCreationDto.getPeopleCapacity());
        return schedule;
    }

    @Override
    @Transactional
    public ScheduleOutputDto save(Schedule schedule) throws InvalidDateTimeException, InvalidRoleException, InvalidCapacityException {
        if(crossingDateTimeInScheduleCheck(schedule)){
            throw new InvalidDateTimeException("dateTime that you picked crosses with other schedule's dateTime");
        }
        if (schedule.getTrainingStartDateTime().isBefore(LocalDateTime.now())) {
            throw new InvalidDateTimeException("trainingStartDateTime must be sooner than now");
        }
        if (schedule.getGym().getPeopleCapacity().compareTo(schedule.getPeopleCapacity()) < 0) {
            throw new InvalidCapacityException("capacity of gym must be >= than capacity of training");
        }
        scheduleRepository.save(schedule);
        return scheduleMapper.toDto(schedule);
    }

    boolean crossingDateTimeInScheduleCheck(Schedule schedule) {
        Gym currentGym = schedule.getGym();
        Employee currentEmployee = schedule.getEmployeeTrainingType().getEmployee();
        return scheduleRepository.findAllByGymOrEmployeeTrainingType_Employee(currentGym, currentEmployee)
                .stream()
                .anyMatch(x ->
                        ((schedule.getTrainingStartDateTime().plusMinutes(schedule.getTrainingDuration())
                                .isAfter(x.getTrainingStartDateTime()))
                                &&
                        (schedule.getTrainingStartDateTime().plusMinutes(schedule.getTrainingDuration())
                                .isBefore(x.getTrainingStartDateTime().plusMinutes(x.getTrainingDuration()))))
                ||
                        ((schedule.getTrainingStartDateTime()
                                .isBefore(x.getTrainingStartDateTime().plusMinutes(x.getTrainingDuration())))
                                &&
                        (schedule.getTrainingStartDateTime())
                                .isAfter(x.getTrainingStartDateTime()))
                ||
                        ((schedule.getTrainingStartDateTime()
                                .isBefore(x.getTrainingStartDateTime())
                                &&
                        (schedule.getTrainingStartDateTime().plusMinutes(schedule.getTrainingDuration())
                                .isAfter(x.getTrainingStartDateTime().plusMinutes(x.getTrainingDuration()))))));
    }

    @Override
    public void editById(Long id, Schedule schedule) throws InvalidDateTimeException, InvalidCapacityException, InvalidRoleException {
        Schedule currentSchedule = scheduleRepository.getReferenceById(id);
        currentSchedule.setGym(schedule.getGym());
        currentSchedule.setPeopleCapacity(schedule.getPeopleCapacity());
        currentSchedule.setTrainingDuration(schedule.getTrainingDuration());
        currentSchedule.setTrainingStartDateTime(schedule.getTrainingStartDateTime());
        currentSchedule.setEmployeeTrainingType(schedule.getEmployeeTrainingType());
        save(schedule);
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
