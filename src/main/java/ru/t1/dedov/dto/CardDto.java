package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CardDto {

    @JsonIgnore
    private Long id;

    private LocalDateTime dateOfPurchase;

    private LocalDateTime dateOfExpiration;

    private Integer price;
}
