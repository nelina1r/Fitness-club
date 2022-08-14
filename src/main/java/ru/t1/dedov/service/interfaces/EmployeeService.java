package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto findById(Long id);

    EmployeeDto save(EmployeeDto employeeDto);

    void deleteById(Long id);
}
