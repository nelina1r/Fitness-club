package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.TrainingTypeDto;
import ru.t1.dedov.model.entity.TrainingType;

@Mapper(componentModel = "spring")
public interface TrainingTypeMapper {
    TrainingTypeDto toDto(TrainingType entity);

    TrainingType toEntity(TrainingTypeDto dto);
}
