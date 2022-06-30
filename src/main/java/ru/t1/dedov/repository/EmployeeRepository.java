package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
