package ru.t1.dedov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.t1.dedov.model.entity.enums.Role;

@Data
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    private Long id;

    private String username;

    private String password;

    private Role role;
}
