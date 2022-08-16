package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.model.entity.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleOutputDto toDto(Schedule entity);

}
