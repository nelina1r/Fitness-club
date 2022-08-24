package ru.t1.dedov.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.Gym;
import ru.t1.dedov.model.entity.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByGymOrEmployeeTrainingType_Employee(Gym gym, Employee employee);
}
