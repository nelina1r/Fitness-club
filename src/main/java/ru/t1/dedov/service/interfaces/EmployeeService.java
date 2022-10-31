package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(String search, Pageable page);

    EmployeeDto findById(Long id);

    EmployeeDto save(EmployeeDto employeeDto);

    void deleteById(Long id);

    void editById(Long id, EmployeeDto employeeDto);
}
