package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.ScheduleCreationDto;
import ru.t1.dedov.dto.ScheduleOutputDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidDateTimeException;
import ru.t1.dedov.exceptions.InvalidRoleException;
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
    public ResponseEntity<String> saveSchedule(@RequestBody ScheduleCreationDto scheduleDto) throws InvalidDateTimeException, InvalidRoleException, InvalidCapacityException {
        scheduleService.save(scheduleService.parseSchedule(scheduleDto));
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("edit schedule")
    @PutMapping("/schedule/{id}")
    public ResponseEntity<String> editSchedule(@PathVariable Long id, @RequestBody ScheduleCreationDto scheduleDto) throws InvalidDateTimeException, InvalidCapacityException, InvalidRoleException {
        scheduleService.editById(id, scheduleService.parseSchedule(scheduleDto));
        return ResponseEntity.ok("updated");
    }

    @ApiOperation("find all schedules")
    @GetMapping("/schedule")
    public List<ScheduleOutputDto> findAllSchedules(
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault Pageable page
    ) {
        return scheduleService.findAll(search, page);
    }

    @ApiOperation("find schedule by id")
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleOutputDto> findScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @ApiOperation("delete schedule by id")
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
