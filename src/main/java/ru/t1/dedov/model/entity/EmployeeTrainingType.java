package ru.t1.dedov.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@IdClass(EmployeeTrainingTypeId.class)
@Table(name = "employee_training_type")
public class EmployeeTrainingType implements Serializable {

    @Id
    @ManyToOne
    private Employee employee;

    @Id
    @ManyToOne
    private TrainingType trainingType;

}
