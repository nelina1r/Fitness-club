package ru.t1.dedov.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import ru.t1.dedov.model.entity.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "employee")
public class Employee extends User implements Serializable {

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private Set<Schedule> scheduleList;

    @ManyToMany
    @ToString.Exclude
    private Set<TrainingType> trainingTypes;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "passport", unique = true, nullable = false)
    private String passport;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "home_address", nullable = false)
    private String homeAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "phone_number", length = 14, nullable = false)
    private String phoneNumber;

    @Column(name = "salary", scale = 2, nullable = false)
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
