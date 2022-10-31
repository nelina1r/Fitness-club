package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.filter.service.implement.EmployeeFilterService;
import ru.t1.dedov.mapper.EmployeeMapper;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.repository.EmployeeRepository;
import ru.t1.dedov.model.repository.TrainingTypeRepository;
import ru.t1.dedov.service.interfaces.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeFilterService employeeFilterService;

    @Override
    public List<EmployeeDto> findAll(String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return employeeRepository.findAll(page)
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        else
            return employeeRepository.findAll(employeeFilterService.generateSearchSpecifications(search), page)
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

    @Override
    public void editById(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.getReferenceById(id);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setPassport(employeeDto.getPassport());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setHomeAddress(employeeDto.getHomeAddress());
        employee.setGender(employeeDto.getGender());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

}
