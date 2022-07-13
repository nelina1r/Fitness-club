package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.TrainingType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CardDto {

    @JsonIgnore
    private Long id;

    private LocalDateTime dateOfPurchase;

    private LocalDateTime dateOfExpiration;

    private BigDecimal price;

    private Client client;

    private List<TrainingType> trainingTypes;
}
