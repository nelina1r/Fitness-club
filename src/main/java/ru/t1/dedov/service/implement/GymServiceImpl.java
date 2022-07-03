package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.mapper.GymMapper;
import ru.t1.dedov.model.repository.GymRepository;
import ru.t1.dedov.service.interfaces.GymService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;
    private final GymMapper gymMapper;

    @Override
    public List<GymDto> findAll() {
        return gymRepository.findAll()
                .stream()
                .map(gymMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GymDto findById(Long id) {
        return gymMapper.toDto(gymRepository.findById(id).get());
    }

    @Override
    public GymDto save(GymDto gymDto) {
        gymRepository.save(gymMapper.toEntity(gymDto));
        return gymDto;
    }

    @Override
    public void deleteById(Long id) {
        gymRepository.deleteById(id);
    }
}
