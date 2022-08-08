package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.mapper.ClientMapper;
import ru.t1.dedov.mapper.EmployeeMapper;
import ru.t1.dedov.mapper.UserMapper;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.User;
import ru.t1.dedov.model.entity.enums.Role;
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

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void register(PersonRegistrationDto personRegistrationDto) {
        User user = new User();
        user.setUsername(personRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(personRegistrationDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        if(personRegistrationDto.getSalary() != null){
            Employee employee = new Employee();
            employee.setId(userRepository.findByUsername(user.getUsername()).get().getId());
            employee.setFirstName(personRegistrationDto.getFirstName());
            employee.setLastName(personRegistrationDto.getLastName());
            employee.setPatronymic(personRegistrationDto.getPatronymic());
            employee.setPassport(personRegistrationDto.getPassport());
            employee.setDateOfBirth(personRegistrationDto.getDateOfBirth());
            employee.setHomeAddress(personRegistrationDto.getHomeAddress());
            employee.setGender(personRegistrationDto.getGender());
            employee.setPhoneNumber(personRegistrationDto.getPhoneNumber());
            employee.setSalary(personRegistrationDto.getSalary());
            employeeRepository.save(employee);
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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
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
