package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.model.entity.Card;

import java.util.List;

public interface CardService {
    List<CardDto> findAll(Specification<Card> spec, String search, Pageable page);

    CardDto findById(Long id);

    CardDto save(CardDto cardDto);

    void deleteById(Long id);

    void addTrainingTypeInCard(Long cardId, Long ttId);

    void editById(Long cardId, CardDto cardDto);
}
