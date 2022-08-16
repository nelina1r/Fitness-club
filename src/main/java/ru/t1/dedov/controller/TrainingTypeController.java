package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.TrainingTypeDto;
import ru.t1.dedov.service.interfaces.TrainingTypeService;

import java.util.List;

@Api(value = "Training type controller")
@RestController
@RequestMapping("/api")
public class TrainingTypeController {

    private final TrainingTypeService trainingTypeService;

    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @ApiOperation("save training type")
    @PostMapping("/trainingType")
    public ResponseEntity<String> saveTrainingType(@RequestBody TrainingTypeDto trainingTypeDto) {
        trainingTypeService.save(trainingTypeDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("find add training types")
    @GetMapping("/trainingType")
    public List<TrainingTypeDto> findAllTrainingTypes() {
        return trainingTypeService.findAll();
    }

    @ApiOperation("find training type by id")
    @GetMapping("/trainingType/{id}")
    public ResponseEntity<TrainingTypeDto> findTrainingTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingTypeService.findById(id));
    }

    @ApiOperation("delete training type by id")
    @DeleteMapping("/trainingType/{id}")
    public ResponseEntity<String> deleteTrainingTypeById(@PathVariable Long id) {
        trainingTypeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
