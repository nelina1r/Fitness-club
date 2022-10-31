package ru.t1.dedov.filter.service.implement;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.filter.service.interfaces.FilterService;
import ru.t1.dedov.filter.specification.SpecificationsBuilder;
import ru.t1.dedov.model.entity.Employee;

import java.util.regex.Matcher;

@Service
public class EmployeeFilterService implements FilterService {
    @Override
    public Specification<Employee> generateSearchSpecifications(String search) {
        SpecificationsBuilder<Employee> builder = new SpecificationsBuilder<>();
        Matcher matcher = FilterService.pattern.matcher(search + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        return builder.build();
    }
}
