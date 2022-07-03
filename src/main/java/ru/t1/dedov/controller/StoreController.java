package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.controller.urls.Urls;
import ru.t1.dedov.dto.StoreDto;
import ru.t1.dedov.service.interfaces.StoreService;

import java.util.List;

@RestController
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping(Urls.Store.FULL)
    public ResponseEntity<String> saveOrUpdate(@RequestBody StoreDto storeDto) {
        storeService.save(storeDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(Urls.Store.FULL)
    public List<StoreDto> findAll() {
        return storeService.findAll();
    }

    @GetMapping(Urls.Store.Id.FULL)
    public ResponseEntity<StoreDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.findById(id));
    }

    @DeleteMapping(Urls.Store.Id.FULL)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        storeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
