package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.controller.urls.Urls;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.service.interfaces.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(Urls.Employee.FULL)
    public ResponseEntity<String> saveOrUpdate(@RequestBody EmployeeDto employeeDto) {
        employeeService.save(employeeDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(Urls.Employee.FULL)
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(Urls.Employee.Id.FULL)
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @DeleteMapping(Urls.Employee.Id.FULL)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
