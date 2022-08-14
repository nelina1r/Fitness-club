package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.exceptions.InvalidDataException;
import ru.t1.dedov.service.interfaces.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> saveOrUpdate(@RequestBody ScheduleDto scheduleDto) throws InvalidDataException {
        scheduleService.save(scheduleDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/schedule")
    public List<ScheduleDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
