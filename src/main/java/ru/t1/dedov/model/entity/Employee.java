package ru.t1.dedov.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import ru.t1.dedov.model.entity.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "employee")
public class Employee extends User implements Serializable {

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private List<Schedule> scheduleList;

    @ManyToMany
    @ToString.Exclude
    private List<TrainingType> trainingTypes;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "passport", unique = true)
    private String passport;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "home_address")
    private String homeAddress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number", length = 14)
    private String phoneNumber;

    @Column(name = "salary", scale = 2)
    private BigDecimal salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
