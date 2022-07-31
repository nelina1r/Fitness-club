package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.TrainingType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CardDto {

    @JsonIgnore
    private Long id;

    private LocalDateTime dateOfPurchase;

    private LocalDateTime dateOfExpiration;

    private BigDecimal price;

    private Client client;

    private Set<TrainingType> trainingTypes;
}
