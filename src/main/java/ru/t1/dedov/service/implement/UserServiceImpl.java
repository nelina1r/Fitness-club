package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.filter.service.implement.UserFilterService;
import ru.t1.dedov.mapper.ClientMapper;
import ru.t1.dedov.mapper.EmployeeMapper;
import ru.t1.dedov.mapper.UserMapper;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.TrainingType;
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
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserFilterService userFilterService;

    @Override
    @Transactional
    public void register(PersonRegistrationDto personRegistrationDto) throws InvalidTypeException {
        if(!"employee".equals(personRegistrationDto.getPersonType()) && !"client".equals(personRegistrationDto.getPersonType())){
            throw new InvalidTypeException("personType must be employee or client");
        }
        if(personRegistrationDto.getPersonType().equals("employee")){
            Employee employee = new Employee();
            employee.setUsername(personRegistrationDto.getUsername());
            employee.setPassword(passwordEncoder.encode(personRegistrationDto.getPassword()));
            employee.setRole(Role.TRAINER);
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
        if(personRegistrationDto.getPersonType().equals("client")){
            Client client = new Client();
            client.setUsername(personRegistrationDto.getUsername());
            client.setPassword(passwordEncoder.encode(personRegistrationDto.getPassword()));
            client.setRole(Role.GUEST);
            client.setFirstName(personRegistrationDto.getFirstName());
            client.setLastName(personRegistrationDto.getLastName());
            client.setPatronymic(personRegistrationDto.getPatronymic());
            client.setPassport(personRegistrationDto.getPassport());
            client.setDateOfBirth(personRegistrationDto.getDateOfBirth());
            client.setHomeAddress(personRegistrationDto.getHomeAddress());
            client.setGender(personRegistrationDto.getGender());
            client.setPhoneNumber(personRegistrationDto.getPhoneNumber());
            clientRepository.save(client);
        }
    }

    @Override
    public void giveUserAdminRights(Long id){
        User user = userRepository.getReferenceById(id);
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers(Specification<User> spec, String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return userRepository.findAll(spec, page)
                    .stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        else
            return userRepository.findAll(userFilterService.generateSearchSpecifications(search), page)
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
