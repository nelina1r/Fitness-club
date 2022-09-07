package ru.t1.dedov.service.implement;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDateTimeException;
import ru.t1.dedov.exceptions.InvalidRoleException;
import ru.t1.dedov.model.entity.*;
import ru.t1.dedov.model.repository.*;
import ru.t1.dedov.service.interfaces.ScheduleService;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
class ScheduleServiceImplTest {

    @Autowired
    private ScheduleService scheduleService;
    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private EmployeeTrainingTypeRepository employeeTrainingTypeRepository;

    @Test
    void save_shouldSaveSuccessfully() throws InvalidRoleException, InvalidDateTimeException, InvalidCapacityException {
        final Schedule schedule = new Schedule();
        Gym gym = new Gym();
        gym.setPeopleCapacity(500);
        schedule.setGym(gym);
        schedule.setPeopleCapacity(30);
        schedule.setEmployeeTrainingType(new EmployeeTrainingType(new Employee(), new TrainingType()));
        schedule.setTrainingStartDateTime(LocalDateTime.of(3000, 1, 10, 13, 0));

        ScheduleOutputDto scheduleOutputDto = scheduleService.save(schedule);

        Assert.assertNotNull(scheduleOutputDto);
        Mockito.verify(scheduleRepository, Mockito.times(1)).save(schedule);
    }

    @Test
    void save_shouldThrowInvalidDateTimeException() {
        final Schedule schedule = new Schedule();
        Gym gym = new Gym();
        gym.setPeopleCapacity(500);
        schedule.setGym(gym);
        schedule.setPeopleCapacity(30);
        schedule.setEmployeeTrainingType(new EmployeeTrainingType(new Employee(), new TrainingType()));
        schedule.setTrainingStartDateTime(LocalDateTime.of(1990, 1, 10, 13, 0));

        Assert.assertThrows(InvalidDateTimeException.class, () -> scheduleService.save(schedule));
    }

    @Test
    void save_shouldThrowInvalidCapacityException() {
        final Schedule schedule = new Schedule();
        Gym gym = new Gym();
        gym.setPeopleCapacity(5);
        schedule.setGym(gym);
        schedule.setPeopleCapacity(30);
        schedule.setEmployeeTrainingType(new EmployeeTrainingType(new Employee(), new TrainingType()));
        schedule.setTrainingStartDateTime(LocalDateTime.of(3000, 1, 10, 13, 0));

        Assert.assertThrows(InvalidCapacityException.class, () -> scheduleService.save(schedule));
    }
}