package ru.t1.dedov.service.implement;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.model.entity.EmployeeTrainingType;
import ru.t1.dedov.model.entity.Gym;
import ru.t1.dedov.model.repository.EmployeeTrainingTypeRepository;
import ru.t1.dedov.model.repository.ScheduleRepository;
import ru.t1.dedov.service.interfaces.ScheduleService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceImplTest {

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private EmployeeTrainingTypeRepository employeeTrainingTypeRepository;

    @Test
    void shouldSaveSuccessfully() {
        final ScheduleCreationDto scheduleCreationDto = new ScheduleCreationDto
                (null, 1L, 1L, 1L, LocalDateTime.of(3000, 1, 1, 10, 10, 10), 45, 10);
        Mockito.verify(employeeTrainingTypeRepository, Mockito.times(1)).exists(Example.of(new EmployeeTrainingType()));
    }
}