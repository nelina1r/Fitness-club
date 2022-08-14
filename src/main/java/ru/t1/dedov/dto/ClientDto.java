package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.Schedule;
import ru.t1.dedov.model.entity.enums.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    @JsonIgnore
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String passport;

    private LocalDate dateOfBirth;

    private String homeAddress;

    private Gender gender;

    private String phoneNumber;

    private Set<Card> cardSet;

    private List<Schedule> scheduleList;
}
