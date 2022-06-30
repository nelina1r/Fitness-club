package ru.t1.dedov.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.model.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
