package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.mapper.CardMapper;
import ru.t1.dedov.model.repository.CardRepository;
import ru.t1.dedov.service.interfaces.CardService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public List<CardDto> findAll() {
        return cardRepository.findAll()
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
}
