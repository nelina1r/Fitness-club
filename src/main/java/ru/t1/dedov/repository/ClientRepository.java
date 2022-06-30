package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
