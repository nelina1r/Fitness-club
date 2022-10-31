package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.filter.service.implement.CardFilterService;
import ru.t1.dedov.mapper.CardMapper;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.TrainingType;
import ru.t1.dedov.model.repository.CardRepository;
import ru.t1.dedov.model.repository.TrainingTypeRepository;
import ru.t1.dedov.service.interfaces.CardService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final CardMapper cardMapper;
    private final CardFilterService cardFilterService;

    @Override
    public List<CardDto> findAll(String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return cardRepository.findAll(page)
                    .stream()
                    .map(cardMapper::toDto)
                    .collect(Collectors.toList());
        else
            return cardRepository.findAll(cardFilterService.generateSearchSpecifications(search), page)
                    .stream()
                    .map(cardMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Override
    public CardDto findById(Long id) {
        return cardMapper.toDto(cardRepository.findById(id).get());
    }

    @Override
    @Transactional
    public CardDto save(CardDto cardDto) {
        cardRepository.save(cardMapper.toEntity(cardDto));
        return cardDto;
    }

    @Override
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addTrainingTypeInCard(Long cardId, Long ttId) {
        Card card = cardRepository.getReferenceById(cardId);
        TrainingType trainingType = trainingTypeRepository.getReferenceById(ttId);
        Set<TrainingType> ttSet = card.getTrainingTypes();
        ttSet.add(trainingType);
        card.setTrainingTypes(ttSet);
        cardRepository.save(card);
    }

    @Override
    public void editById(Long cardId, CardDto cardDto) {
        Card card = cardRepository.getReferenceById(cardId);
        card.setDateOfPurchase(cardDto.getDateOfPurchase());
        card.setDateOfExpiration(cardDto.getDateOfExpiration());
        card.setPrice(cardDto.getPrice());
        cardRepository.save(card);
    }
}
