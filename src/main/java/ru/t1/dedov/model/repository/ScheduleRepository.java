package ru.t1.dedov.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.Gym;
import ru.t1.dedov.model.entity.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule> {
    Page<Schedule> findAll(Specification<Schedule> spec, Pageable page);

    List<Schedule> findAllByGymOrEmployeeTrainingType_Employee(Gym gym, Employee employee);
}
