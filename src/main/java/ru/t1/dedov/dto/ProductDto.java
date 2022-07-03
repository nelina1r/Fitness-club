package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {

    @JsonIgnore
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer amount;
}
