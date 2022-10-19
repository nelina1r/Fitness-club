package ru.t1.dedov.filter.service.implement;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.t1.dedov.filter.service.interfaces.Filter;
import ru.t1.dedov.filter.specification.SpecificationsBuilder;
import ru.t1.dedov.model.entity.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientFilterService implements Filter {
    @Override
    public Specification<Client> generateSearchSpecifications(String search) {
        SpecificationsBuilder<Client> builder = new SpecificationsBuilder<>();
        Pattern pattern = Pattern.compile(Filter.pattern);
        Matcher matcher = pattern.matcher(search + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(3));
        }
        return builder.build();
    }
}
