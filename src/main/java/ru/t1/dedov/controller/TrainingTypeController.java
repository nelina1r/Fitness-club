package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.TrainingTypeDto;
import ru.t1.dedov.service.interfaces.TrainingTypeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;

    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    //@PreAuthorize("hasRole('R')")
    @PostMapping("/trainingType")
    public ResponseEntity<String> saveOrUpdate(@RequestBody TrainingTypeDto trainingTypeDto) {
        trainingTypeService.save(trainingTypeDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/trainingType")
    public List<TrainingTypeDto> findAll() {
        return trainingTypeService.findAll();
    }

    @GetMapping("/trainingType/{id}")
    public ResponseEntity<TrainingTypeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingTypeService.findById(id));
    }

    @DeleteMapping("/trainingType/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        trainingTypeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
