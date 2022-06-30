package ru.t1.dedov.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.model.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
