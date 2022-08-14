package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.EmployeeDto;
import ru.t1.dedov.service.interfaces.EmployeeService;

import java.util.List;

@Api(value = "Employee controller")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation("save employee")
    @PostMapping("/employee")
    public ResponseEntity<String> saveOrUpdate(@RequestBody EmployeeDto employeeDto) {
        employeeService.save(employeeDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("find all employees")
    @GetMapping("/employee")
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @ApiOperation("find employee by id")
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @ApiOperation("delete employee by id")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}