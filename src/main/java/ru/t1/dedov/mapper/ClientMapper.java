package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.model.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client entity);

    Client toEntity(ClientDto dto);
}
