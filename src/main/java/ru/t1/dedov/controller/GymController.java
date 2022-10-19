package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.GymDto;
import ru.t1.dedov.model.entity.Gym;
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
    public ResponseEntity<String> saveGym(@RequestBody GymDto gymDto) {
        gymService.save(gymDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("edit gym")
    @PutMapping("/gym/{id}")
    public ResponseEntity<String> editGym(@PathVariable Long id, @RequestBody GymDto gymDto) {
        gymService.editById(id, gymDto);
        return ResponseEntity.ok("updated");
    }

    @ApiOperation("find all gyms")
    @GetMapping("/gym")
    public List<GymDto> findAllGyms(
            @Spec(path = "id", paramSeparator = ',', spec = Like.class) Specification<Gym> spec,
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault Pageable page
    ) {
        return gymService.findAll(spec, search, page);
    }

    @ApiOperation("find gym by id")
    @GetMapping("/gym/{id}")
    public ResponseEntity<GymDto> findGymById(@PathVariable Long id) {
        return ResponseEntity.ok(gymService.findById(id));
    }

    @ApiOperation("delete gym by id")
    @DeleteMapping("/gym/{id}")
    public ResponseEntity<String> deleteGymById(@PathVariable Long id) {
        gymService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
