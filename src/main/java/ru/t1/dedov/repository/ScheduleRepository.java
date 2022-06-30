package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
