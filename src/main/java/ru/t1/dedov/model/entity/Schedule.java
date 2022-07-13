package ru.t1.dedov.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(
        name = "schedule",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "training_start_date_time"})
)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToMany
    private List<Client> clientList;

    @ManyToOne
    private Gym gym;

    @Column(name = "training_start_date_time")
    private LocalDateTime trainingStartDateTime;

    @Column(name = "training_duration")
    private Integer trainingDuration;

    @Column(name = "people_capacity")
    private Integer peopleCapacity;

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
