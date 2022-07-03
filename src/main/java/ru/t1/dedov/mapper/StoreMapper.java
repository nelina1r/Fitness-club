package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.StoreDto;
import ru.t1.dedov.model.entity.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreDto toDto(Store entity);

    Store toEntity(StoreDto dto);
}
