package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.Gym;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ScheduleDto {

    @JsonIgnore
    private Long id;

    private Employee employee;

    private Client client;

    private Gym gym;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
