package ru.t1.dedov.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Client> clientList;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Gym> gymList;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
}
