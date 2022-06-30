package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDto {

    @JsonIgnore
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String passport;

    private Role role;

    private LocalDate dateOfBirth;

    private String homeAddress;

    private String sex;

    private String phoneNumber;

    private BigDecimal salary;
}
