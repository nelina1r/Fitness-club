package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
