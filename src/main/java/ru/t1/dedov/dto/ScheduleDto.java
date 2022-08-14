package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ScheduleDto {

    @JsonIgnore
    private Long id;

    private EmployeeTrainingType employeeTrainingType;

    private Gym gym;

    private LocalDateTime trainingStartDateTime;

    private Integer trainingDuration;

    private Integer peopleCapacity;

    private Set<Client> clientSet;
}
