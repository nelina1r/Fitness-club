package ru.t1.dedov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.dedov.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
