package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.mapper.ScheduleMapper;
import ru.t1.dedov.model.repository.ScheduleRepository;
import ru.t1.dedov.service.interfaces.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDto findById(Long id) {
        return scheduleMapper.toDto(scheduleRepository.findById(id).get());
    }

    @Override
    public ScheduleDto save(ScheduleDto scheduleDto) {
        scheduleRepository.save(scheduleMapper.toEntity(scheduleDto));
        return scheduleDto;
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
