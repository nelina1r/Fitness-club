package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
