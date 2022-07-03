package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Store;

@Data
@NoArgsConstructor
public class GymDto {

    @JsonIgnore
    private Long id;

    private String address;

    private String phoneNumber;

    private Store store;
}
