package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.EmployeeTrainingType;
import ru.t1.dedov.model.entity.Gym;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ScheduleOutputDto {

    @JsonIgnore
    private Long id;

    private Gym gym;

    private EmployeeTrainingType employeeTrainingType;

    private LocalDateTime trainingStartDateTime;

    private Integer trainingDuration;

    private Integer peopleCapacity;

    private Set<Client> clientSet;
}
