package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDateTimeException;
import ru.t1.dedov.exceptions.InvalidRoleException;
import ru.t1.dedov.mapper.ScheduleMapper;
import ru.t1.dedov.model.entity.*;
import ru.t1.dedov.model.repository.*;
import ru.t1.dedov.service.interfaces.ScheduleService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Period;
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

    @Override
    public List<ScheduleOutputDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleOutputDto findById(Long id) {
        return scheduleMapper.toDto(scheduleRepository.findById(id).get());
    }

    @Override
    @Transactional
    public ScheduleOutputDto save(ScheduleCreationDto scheduleCreationDto) throws InvalidDateTimeException, InvalidRoleException, InvalidCapacityException {
        Schedule schedule = new Schedule();
        //
        EmployeeTrainingType employeeTrainingType = new EmployeeTrainingType();
        Employee employee = employeeRepository.getReferenceById(scheduleCreationDto.getEmployeeId());
        employeeTrainingType.setEmployee(employee);
        employeeTrainingType.setTrainingType(trainingTypeRepository.getReferenceById(scheduleCreationDto.getTrainingTypeId()));
        if (!employeeTTRepository.exists(Example.of(employeeTrainingType))) {
            employeeTTRepository.save(employeeTrainingType);
        }
        //
        Gym gym = gymRepository.getReferenceById(scheduleCreationDto.getGymId());
        //
        if(crossingDateTimeInScheduleCheck(gym, employee, scheduleCreationDto)){
            throw new InvalidDateTimeException("dateTime that you picked crosses with other schedule's dateTime");
        }
        if (scheduleCreationDto.getTrainingStartDateTime().isBefore(LocalDateTime.now())) {
            throw new InvalidDateTimeException("trainingStartDateTime must be sooner than now");
        }
        if (gym.getPeopleCapacity().compareTo(scheduleCreationDto.getPeopleCapacity()) < 0) {
            throw new InvalidCapacityException("capacity of gym must be >= than capacity of training");
        }
        //
        schedule.setGym(gym);
        schedule.setEmployeeTrainingType(employeeTrainingType);
        schedule.setTrainingStartDateTime(scheduleCreationDto.getTrainingStartDateTime());
        schedule.setTrainingDuration(scheduleCreationDto.getTrainingDuration());
        schedule.setPeopleCapacity(scheduleCreationDto.getPeopleCapacity());
        scheduleRepository.save(schedule);
        return scheduleMapper.toDto(schedule);
    }

    boolean crossingDateTimeInScheduleCheck(Gym currentGym, Employee currentEmployee, ScheduleCreationDto scheduleDto) {
        return scheduleRepository.findAll().stream()
                .filter(x -> (x.getGym().equals(currentGym) || (x.getEmployeeTrainingType().getEmployee().equals(currentEmployee))))
                .anyMatch(x ->
                        ((scheduleDto.getTrainingStartDateTime().plusMinutes(scheduleDto.getTrainingDuration())
                                .isAfter(x.getTrainingStartDateTime()))
                                &&
                        (scheduleDto.getTrainingStartDateTime().plusMinutes(scheduleDto.getTrainingDuration())
                                .isBefore(x.getTrainingStartDateTime().plusMinutes(x.getTrainingDuration()))))
                ||
                        ((scheduleDto.getTrainingStartDateTime()
                                .isBefore(x.getTrainingStartDateTime().plusMinutes(x.getTrainingDuration())))
                                &&
                        (scheduleDto.getTrainingStartDateTime())
                                .isAfter(x.getTrainingStartDateTime()))
                ||
                        ((scheduleDto.getTrainingStartDateTime()
                                .isBefore(x.getTrainingStartDateTime())
                                &&
                        (scheduleDto.getTrainingStartDateTime().plusMinutes(scheduleDto.getTrainingDuration())
                                .isAfter(x.getTrainingStartDateTime().plusMinutes(x.getTrainingDuration()))))));
    }

    @Override
    public void editById(Long id, ScheduleCreationDto scheduleCreationDto) throws InvalidDateTimeException, InvalidCapacityException {
        Schedule schedule = scheduleRepository.getReferenceById(id);
        EmployeeTrainingType employeeTrainingType = new EmployeeTrainingType();
        Employee employee = employeeRepository.getReferenceById(scheduleCreationDto.getEmployeeId());
        employeeTrainingType.setEmployee(employee);
        employeeTrainingType.setTrainingType(trainingTypeRepository.getReferenceById(scheduleCreationDto.getTrainingTypeId()));
        if (!employeeTTRepository.exists(Example.of(employeeTrainingType))) {
            employeeTTRepository.save(employeeTrainingType);
        }
        Gym gym = gymRepository.getReferenceById(scheduleCreationDto.getGymId());
        if(crossingDateTimeInScheduleCheck(gym, employee, scheduleCreationDto)){
            throw new InvalidDateTimeException("dateTime that you picked crosses with other schedule's dateTime");
        }
        if (scheduleCreationDto.getTrainingStartDateTime().isBefore(LocalDateTime.now())) {
            throw new InvalidDateTimeException("trainingStartDateTime must be sooner than now");
        }
        if (gym.getPeopleCapacity().compareTo(scheduleCreationDto.getPeopleCapacity()) < 0) {
            throw new InvalidCapacityException("capacity of gym must be >= than capacity of training");
        }
        schedule.setGym(gym);
        schedule.setEmployeeTrainingType(employeeTrainingType);
        schedule.setTrainingStartDateTime(scheduleCreationDto.getTrainingStartDateTime());
        schedule.setTrainingDuration(scheduleCreationDto.getTrainingDuration());
        schedule.setPeopleCapacity(scheduleCreationDto.getPeopleCapacity());
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
