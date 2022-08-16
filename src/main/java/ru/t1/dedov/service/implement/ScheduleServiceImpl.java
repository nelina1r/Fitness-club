package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDataException;
import ru.t1.dedov.exceptions.InvalidRoleException;
import ru.t1.dedov.mapper.ScheduleMapper;
import ru.t1.dedov.model.entity.*;
import ru.t1.dedov.model.entity.enums.Role;
import ru.t1.dedov.model.repository.*;
import ru.t1.dedov.service.interfaces.ScheduleService;

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
    public ScheduleOutputDto save(ScheduleCreationDto scheduleCreationDto) throws InvalidDataException, InvalidRoleException, InvalidCapacityException {
        Schedule schedule = new Schedule();
        //
        Employee employee = employeeRepository.getReferenceById(scheduleCreationDto.getEmployeeId());
        TrainingType trainingType = trainingTypeRepository.getReferenceById(scheduleCreationDto.getTrainingTypeId());
        EmployeeTrainingType employeeTrainingType = new EmployeeTrainingType();

        employeeTrainingType.setEmployee(employee);
        employeeTrainingType.setTrainingType(trainingType);
        employeeTTRepository.save(employeeTrainingType);
        //
        schedule.setEmployeeTrainingType(employeeTrainingType);
        Gym gym = gymRepository.getReferenceById(scheduleCreationDto.getGymId());
        schedule.setGym(gym);
        schedule.setTrainingStartDateTime(scheduleCreationDto.getTrainingStartDateTime());
        schedule.setTrainingDuration(scheduleCreationDto.getTrainingDuration());
        schedule.setPeopleCapacity(scheduleCreationDto.getPeopleCapacity());
        if (schedule.getTrainingStartDateTime().isBefore(LocalDateTime.now())) {
            throw new InvalidDataException("trainingStartDateTime must be sooner than now");
        }
        if (!schedule.getEmployeeTrainingType().getEmployee().getRole().equals(Role.TRAINER)) {
            throw new InvalidRoleException("employee must be trainer to create a schedule");
        }
        if (gym.getPeopleCapacity().compareTo(scheduleCreationDto.getPeopleCapacity()) < 0 ||
                !gym.getPeopleCapacity().equals(scheduleCreationDto.getPeopleCapacity())){
            throw new InvalidCapacityException("capacity of gym must be >= than capacity of training");
        }
            scheduleRepository.save(schedule);
        return scheduleMapper.toDto(schedule);
    }

    @Override
    public void editById(Long id, ScheduleCreationDto scheduleDto) {
        Schedule schedule = scheduleRepository.getReferenceById(id);
        schedule.setTrainingStartDateTime(scheduleDto.getTrainingStartDateTime());
        schedule.setTrainingDuration(schedule.getTrainingDuration());
        //schedule.setGym(scheduleDto.getGym());
        schedule.setPeopleCapacity(scheduleDto.getPeopleCapacity());
        schedule.setEmployeeTrainingType(schedule.getEmployeeTrainingType());
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
