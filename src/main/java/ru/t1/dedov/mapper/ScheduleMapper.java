package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.model.entity.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleDto toDto(Schedule entity);

    Schedule toEntity(ScheduleDto dto);
}
