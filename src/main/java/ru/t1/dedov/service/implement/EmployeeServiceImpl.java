package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.mapper.EmployeeMapper;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.TrainingType;
import ru.t1.dedov.model.repository.EmployeeRepository;
import ru.t1.dedov.model.repository.TrainingTypeRepository;
import ru.t1.dedov.service.interfaces.EmployeeService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id).get());
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        employeeRepository.save(employeeMapper.toEntity(employeeDto));
        return employeeDto;
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
