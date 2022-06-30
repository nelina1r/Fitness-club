package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.model.entity.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto toDto(Card entity);

    Card toEntity(CardDto dto);
}
