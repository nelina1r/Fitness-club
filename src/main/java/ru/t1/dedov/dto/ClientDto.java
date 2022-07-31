package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.Schedule;
import ru.t1.dedov.model.entity.enums.Gender;
import ru.t1.dedov.model.entity.enums.Role;

import java.time.LocalDate;
import java.util.List;

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

    private List<Card> cardList;

    private List<Schedule> scheduleList;
}
