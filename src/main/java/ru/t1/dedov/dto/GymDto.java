package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Schedule;

import java.util.List;

@Data
@NoArgsConstructor
public class GymDto {

    @JsonIgnore
    private Long id;

    private String name;

    private Integer peopleCapacity;

    private List<Schedule> scheduleList;
}
