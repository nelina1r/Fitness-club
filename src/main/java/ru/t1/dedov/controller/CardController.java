package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.service.interfaces.CardService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    public ResponseEntity<String> saveOrUpdate(@RequestBody CardDto cardDto) {
        cardService.save(cardDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/card")
    public List<CardDto> findAll() {
        return cardService.findAll();
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        cardService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
