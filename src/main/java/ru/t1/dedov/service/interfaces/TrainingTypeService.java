package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.TrainingTypeDto;
import ru.t1.dedov.model.entity.TrainingType;

import java.util.List;

public interface TrainingTypeService {
    List<TrainingTypeDto> findAll(Specification<TrainingType> spec, String search, Pageable page);

    TrainingTypeDto findById(Long id);

    TrainingTypeDto save(TrainingTypeDto trainingTypeDto);

    void deleteById(Long id);
}
