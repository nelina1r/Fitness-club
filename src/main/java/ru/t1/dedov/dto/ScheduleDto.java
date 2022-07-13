package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.Gym;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ScheduleDto {

    @JsonIgnore
    private Long id;

    private Employee employee;

    private Gym gym;

    private LocalDateTime trainingStartDateTime;

    private Integer trainingDuration;

    private Integer peopleCapacity;

    private List<Client> clientList;
}
