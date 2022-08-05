package ru.t1.dedov.model.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class EmployeeTrainingTypeId implements Serializable {

    private Employee employee;

    private TrainingType trainingType;
}
