package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.model.entity.Gym;

import java.util.List;

public interface GymService {
    List<GymDto> findAll(Specification<Gym> spec, String search, Pageable page);

    GymDto findById(Long id);

    GymDto save(GymDto gymDto);

    void deleteById(Long id);

    void editById(Long id, GymDto gymDto);
}
