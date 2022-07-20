package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.enums.Gender;
import ru.t1.dedov.model.entity.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PersonRegistrationDto {

    @JsonIgnore
    private Long id;

    private String username;

    private String password;

    private Role role;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String passport;

    private LocalDate dateOfBirth;

    private String homeAddress;

    private Gender gender;

    private String phoneNumber;

    private BigDecimal salary;
}
