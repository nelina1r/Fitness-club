package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.model.entity.User;

import java.util.List;

public interface UserService {

    void register(PersonRegistrationDto personRegistrationDto);

    List<UserDto> getAllUsers();

    User findByUsername(String username);

    UserDto findUserById(Long id);

    void deleteUserById(Long id);

    //UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
