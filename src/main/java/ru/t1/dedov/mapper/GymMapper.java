package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.model.entity.Gym;

@Mapper(componentModel = "spring")
public interface GymMapper {

    GymDto toDto(Gym entity);

    Gym toEntity(GymDto dto);
}
