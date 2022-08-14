package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.service.interfaces.GymService;

import java.util.List;

@Api(value = "Gym controller")
@RestController
@RequestMapping("/api")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @ApiOperation("save gym")
    @PostMapping("/gym")
    public ResponseEntity<String> saveOrUpdate(@RequestBody GymDto gymDto) {
        gymService.save(gymDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("find all gyms")
    @GetMapping("/gym")
    public List<GymDto> findAll() {
        return gymService.findAll();
    }

    @ApiOperation("find gym by id")
    @GetMapping("/gym/{id}")
    public ResponseEntity<GymDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gymService.findById(id));
    }

    @ApiOperation("delete gym by id")
    @DeleteMapping("/gym/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        gymService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
