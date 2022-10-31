package ru.t1.dedov.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.t1.dedov.model.entity.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long>, JpaSpecificationExecutor<Gym> {
    Page<Gym> findAll(Specification<Gym> spec, Pageable page);
}
