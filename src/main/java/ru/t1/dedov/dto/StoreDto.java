package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.Product;

import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto {

    @JsonIgnore
    private Long id;

    private Employee employee;

    private List<Product> productList;
}
