package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.model.entity.TrainingType;
import ru.t1.dedov.model.entity.User;

import java.util.List;

public interface UserService {

    void register(PersonRegistrationDto personRegistrationDto) throws InvalidTypeException;

    List<UserDto> getAllUsers(Specification<User> spec, String search, Pageable page);

    void giveUserAdminRights(Long id);

    User findByUsername(String username);

    UserDto findUserById(Long id);

    void deleteUserById(Long id);
}
