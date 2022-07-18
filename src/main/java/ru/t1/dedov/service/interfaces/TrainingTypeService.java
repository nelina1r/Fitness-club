package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.TrainingTypeDto;

import java.util.List;

public interface TrainingTypeService {
    List<TrainingTypeDto> findAll();

    TrainingTypeDto findById(Long id);

    TrainingTypeDto save(TrainingTypeDto scheduleDto);

    void deleteById(Long id);
}
