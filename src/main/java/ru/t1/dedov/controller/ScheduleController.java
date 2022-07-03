package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.controller.urls.Urls;
import ru.t1.dedov.dto.ScheduleDto;
import ru.t1.dedov.service.interfaces.ScheduleService;

import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping(Urls.Schedule.FULL)
    public ResponseEntity<String> saveOrUpdate(@RequestBody ScheduleDto scheduleDto) {
        scheduleService.save(scheduleDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(Urls.Schedule.FULL)
    public List<ScheduleDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping(Urls.Schedule.Id.FULL)
    public ResponseEntity<ScheduleDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @DeleteMapping(Urls.Schedule.Id.FULL)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
