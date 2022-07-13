package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.Employee;

import java.util.List;

@Data
@NoArgsConstructor
public class TrainingTypeDto {

    @JsonIgnore
    private Long id;

    private String name;

    private List<Employee> employeeList;

    private List<Card> cardList;
}
