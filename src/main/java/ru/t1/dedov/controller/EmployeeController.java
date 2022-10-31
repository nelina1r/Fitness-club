package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.save(employeeDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("edit employee")
    @PutMapping("/employee/{id}")
    public ResponseEntity<String> editEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeService.editById(id, employeeDto);
        return ResponseEntity.ok("updated");
    }

    @ApiOperation("find all employees")
    @GetMapping("/employee")
    public List<EmployeeDto> findAllEmployees(
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault Pageable page
    ) {
        return employeeService.findAll(search, page);
    }

    @ApiOperation("delete employee by id")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
    
    @ApiOperation("find employee by id")
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }


}