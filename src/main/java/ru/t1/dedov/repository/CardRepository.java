package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
