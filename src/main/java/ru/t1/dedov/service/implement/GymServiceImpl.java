package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.filter.service.implement.GymFilterService;
import ru.t1.dedov.mapper.GymMapper;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.Gym;
import ru.t1.dedov.model.repository.GymRepository;
import ru.t1.dedov.service.interfaces.GymService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {
    private final GymRepository gymRepository;
    private final GymMapper gymMapper;
    private final GymFilterService gymFilterService;

    @Override
    public List<GymDto> findAll(String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return gymRepository.findAll(page)
                    .stream()
                    .map(gymMapper::toDto)
                    .collect(Collectors.toList());
        else
            return gymRepository.findAll(gymFilterService.generateSearchSpecifications(search), page)
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

    @Override
    public void editById(Long id, GymDto gymDto) {
        Gym gym = gymRepository.getReferenceById(id);
        gym.setName(gymDto.getName());
        gym.setPeopleCapacity(gymDto.getPeopleCapacity());
        gymRepository.save(gym);
    }
}
