package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee entity);

    Employee toEntity(EmployeeDto dto);

    Employee toEntity(PersonRegistrationDto personRegistrationDto);
}
