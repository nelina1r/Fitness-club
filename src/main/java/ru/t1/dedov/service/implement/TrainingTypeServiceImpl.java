package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.TrainingTypeDto;
import ru.t1.dedov.filter.service.implement.TrainingTypeFilterService;
import ru.t1.dedov.mapper.TrainingTypeMapper;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.TrainingType;
import ru.t1.dedov.model.repository.TrainingTypeRepository;
import ru.t1.dedov.service.interfaces.TrainingTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeMapper trainingTypeMapper;
    private final TrainingTypeFilterService trainingTypeFilterService;

    @Override
    public List<TrainingTypeDto> findAll(Specification<TrainingType> spec, String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return trainingTypeRepository.findAll(spec, page)
                    .stream()
                    .map(trainingTypeMapper::toDto)
                    .collect(Collectors.toList());
        else
            return trainingTypeRepository.findAll(trainingTypeFilterService.generateSearchSpecifications(search), page)
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
