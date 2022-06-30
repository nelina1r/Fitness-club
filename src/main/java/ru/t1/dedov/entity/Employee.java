package ru.t1.dedov.entity;

import lombok.Data;
import ru.t1.dedov.entity.enums.Role;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "passport")
    private String passport;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "salary")
    private BigDecimal salary;
}
