package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.mapper.ClientMapper;
import ru.t1.dedov.mapper.EmployeeMapper;
import ru.t1.dedov.mapper.UserMapper;
import ru.t1.dedov.model.entity.User;
import ru.t1.dedov.model.repository.ClientRepository;
import ru.t1.dedov.model.repository.EmployeeRepository;
import ru.t1.dedov.model.repository.UserRepository;
import ru.t1.dedov.service.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final UserMapper userMapper;
    private final EmployeeMapper employeeMapper;
    private final ClientMapper clientMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(PersonRegistrationDto personRegistrationDto) {
        User user = User.builder()
                .username(personRegistrationDto.getUsername())
                .password(passwordEncoder.encode(personRegistrationDto.getPassword()))
                .role(personRegistrationDto.getRole())
                .build();
        userRepository.save(user);
        if (personRegistrationDto.getSalary() != null) {
            EmployeeDto employeeDto = EmployeeDto.builder()
                    .firstName(personRegistrationDto.getFirstName())
                    .lastName(personRegistrationDto.getLastName())
                    .patronymic(personRegistrationDto.getPatronymic())
                    .passport(personRegistrationDto.getPassport())
                    .dateOfBirth(personRegistrationDto.getDateOfBirth())
                    .homeAddress(personRegistrationDto.getHomeAddress())
                    .gender(personRegistrationDto.getGender())
                    .phoneNumber(personRegistrationDto.getPhoneNumber())
                    .salary(personRegistrationDto.getSalary())
                    .build();
            employeeRepository.save(employeeMapper.toEntity(employeeDto));
        } else {
            ClientDto clientDto = ClientDto.builder()
                    .firstName(personRegistrationDto.getFirstName())
                    .lastName(personRegistrationDto.getLastName())
                    .patronymic(personRegistrationDto.getPatronymic())
                    .passport(personRegistrationDto.getPassport())
                    .dateOfBirth(personRegistrationDto.getDateOfBirth())
                    .homeAddress(personRegistrationDto.getHomeAddress())
                    .gender(personRegistrationDto.getGender())
                    .phoneNumber(personRegistrationDto.getPhoneNumber())
                    .build();
            clientRepository.save(clientMapper.toEntity(clientDto));
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).get());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
