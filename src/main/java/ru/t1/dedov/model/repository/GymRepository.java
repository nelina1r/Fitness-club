package ru.t1.dedov.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.model.entity.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
