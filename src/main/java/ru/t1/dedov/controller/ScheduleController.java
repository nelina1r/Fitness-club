package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.exceptions.InvalidDataException;
import ru.t1.dedov.service.interfaces.ScheduleService;

import java.util.List;

@Api(value = "Schedule controller")
@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ApiOperation("save schedule")
    @PostMapping("/schedule")
    public ResponseEntity<String> saveOrUpdate(@RequestBody ScheduleDto scheduleDto) throws InvalidDataException {
        scheduleService.save(scheduleDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("find all schedules")
    @GetMapping("/schedule")
    public List<ScheduleDto> findAll() {
        return scheduleService.findAll();
    }

    @ApiOperation("find schedule by id")
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @ApiOperation("delete schedule by id")
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
