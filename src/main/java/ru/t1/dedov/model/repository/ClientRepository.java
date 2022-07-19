package ru.t1.dedov.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1.dedov.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
