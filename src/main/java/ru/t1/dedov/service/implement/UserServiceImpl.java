package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.service.interfaces.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public void register(PersonRegistrationDto personRegistrationDto) {

    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto findByUsername(String username) {
        return null;
    }

    @Override
    public UserDto findUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
