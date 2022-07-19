package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.service.interfaces.GymService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping("/gym")
    public ResponseEntity<String> saveOrUpdate(@RequestBody GymDto gymDto) {
        gymService.save(gymDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/gym")
    public List<GymDto> findAll() {
        return gymService.findAll();
    }

    @GetMapping("/gym/{id}")
    public ResponseEntity<GymDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gymService.findById(id));
    }

    @DeleteMapping("/gym/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        gymService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
