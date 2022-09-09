package ru.t1.dedov.service.implement;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.Employee;
import ru.t1.dedov.model.entity.enums.Gender;
import ru.t1.dedov.model.entity.enums.Role;
import ru.t1.dedov.model.repository.ClientRepository;
import ru.t1.dedov.model.repository.EmployeeRepository;
import ru.t1.dedov.service.interfaces.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private ClientRepository clientRepository;

    private final PersonRegistrationDto personRegistrationDto = new PersonRegistrationDto();

    @BeforeEach
    void init() {
        personRegistrationDto.setUsername("");
        personRegistrationDto.setPassword("");
        personRegistrationDto.setRole(Role.TRAINER);
        personRegistrationDto.setFirstName("");
        personRegistrationDto.setLastName("");
        personRegistrationDto.setPatronymic("");
        personRegistrationDto.setPassport("");
        personRegistrationDto.setDateOfBirth(LocalDate.of(1990, 1, 1));
        personRegistrationDto.setHomeAddress("");
        personRegistrationDto.setGender(Gender.FEMALE);
        personRegistrationDto.setPhoneNumber("");
        personRegistrationDto.setSalary(BigDecimal.valueOf(1000));
    }

    @Test
    void register_shouldRegisterEmployeeSuccessfully() throws InvalidTypeException {
        personRegistrationDto.setPersonType("employee");

        userService.register(personRegistrationDto);

        Mockito.verify(employeeRepository, Mockito.times(1)).save(ArgumentMatchers.any(Employee.class));
    }

    @Test
    void register_shouldRegisterClientSuccessfully() throws InvalidTypeException {
        personRegistrationDto.setPersonType("client");

        userService.register(personRegistrationDto);

        Mockito.verify(clientRepository, Mockito.times(1)).save(ArgumentMatchers.any(Client.class));
    }

    @Test
    void register_shouldThrowInvalidTypeException(){
        personRegistrationDto.setPersonType("wrong");

        Assert.assertThrows(InvalidTypeException.class, () -> userService.register(personRegistrationDto));
    }
}