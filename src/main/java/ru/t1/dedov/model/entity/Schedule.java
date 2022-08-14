package ru.t1.dedov.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE card SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(
        name = "schedule",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_training_type_employee_id", "training_start_date_time"})
)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotNull
    private EmployeeTrainingType employeeTrainingType;

    @ManyToMany
    @JsonBackReference
    @ToString.Exclude
    private Set<Client> clientSet;

    @ManyToOne
    @NotNull
    private Gym gym;

    @Column(name = "training_start_date_time", nullable = false)
    private LocalDateTime trainingStartDateTime;

    @Column(name = "training_duration", nullable = false)
    private Integer trainingDuration;

    @Column(name = "people_capacity", nullable = false)
    private Integer peopleCapacity;

    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Schedule schedule = (Schedule) o;
        return id != null && Objects.equals(id, schedule.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
