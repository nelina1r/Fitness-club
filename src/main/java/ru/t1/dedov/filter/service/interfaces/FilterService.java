package ru.t1.dedov.filter.service.interfaces;

import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Pattern;

public interface FilterService {

    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)(),", Pattern.UNICODE_CHARACTER_CLASS);

    Specification<?> generateSearchSpecifications(String search);
}
