package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.StoreDto;
import ru.t1.dedov.mapper.StoreMapper;
import ru.t1.dedov.model.repository.StoreRepository;
import ru.t1.dedov.service.interfaces.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public List<StoreDto> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto findById(Long id) {
        return storeMapper.toDto(storeRepository.findById(id).get());
    }

    @Override
    public StoreDto save(StoreDto storeDto) {
        storeRepository.save(storeMapper.toEntity(storeDto));
        return storeDto;
    }

    @Override
    public void deleteById(Long id) {
        storeRepository.deleteById(id);
    }
}
