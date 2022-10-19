package ru.t1.dedov.filter.service.interfaces;

import org.springframework.data.jpa.domain.Specification;

public interface Filter {
    String pattern = "(\\w+?)(:|<|>)(\\w+?),";

    Specification<?> generateSearchSpecifications(String search);
}
