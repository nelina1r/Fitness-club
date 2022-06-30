package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Card;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClientDto {

    @JsonIgnore
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String passport;

    private LocalDateTime dateOfBirth;

    private String homeAddress;

    private String sex;

    private String phoneNumber;

    private Card card;
}
