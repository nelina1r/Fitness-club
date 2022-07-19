package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.TrainingTypeDto;
import ru.t1.dedov.mapper.TrainingTypeMapper;
import ru.t1.dedov.model.repository.TrainingTypeRepository;
import ru.t1.dedov.service.interfaces.TrainingTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeMapper trainingTypeMapper;
    @Override
    public List<TrainingTypeDto> findAll() {
        return trainingTypeRepository.findAll()
                .stream()
                .map(trainingTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingTypeDto findById(Long id) {
        return trainingTypeMapper.toDto(trainingTypeRepository.findById(id).get());
    }

    @Override
    public TrainingTypeDto save(TrainingTypeDto trainingTypeDto) {
        trainingTypeRepository.save(trainingTypeMapper.toEntity(trainingTypeDto));
        return trainingTypeDto;
    }

    @Override
    public void deleteById(Long id) {
        trainingTypeRepository.deleteById(id);
    }
}
