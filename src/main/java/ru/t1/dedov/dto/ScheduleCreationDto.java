package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ScheduleCreationDto {

    @JsonIgnore
    private Long id;

    private Long employeeId;

    private Long trainingTypeId;

    private Long gymId;

    private LocalDateTime trainingStartDateTime;

    private Integer trainingDuration;

    private Integer peopleCapacity;
}
