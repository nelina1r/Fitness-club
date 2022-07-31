package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;

import java.util.List;

public interface UserService {

    void register(PersonRegistrationDto personRegistrationDto);

    List<UserDto> getAllUsers();

    UserDto findByUsername(String username);

    UserDto findUserById(Long id);

    void deleteUserById(Long id);

    //UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
