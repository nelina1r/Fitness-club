package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);

    User toEntity(UserDto dto);
}
