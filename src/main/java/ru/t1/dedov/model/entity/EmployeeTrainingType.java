package ru.t1.dedov.model.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@IdClass(EmployeeTrainingTypeId.class)
@Table(name = "employee_training_type")
public class EmployeeTrainingType {

    @Id
    @ManyToOne
    private Employee employee;

    @Id
    @ManyToOne
    private TrainingType trainingType;

}
