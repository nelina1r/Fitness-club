package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.controller.urls.Urls;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.service.interfaces.CardService;

import java.util.List;

@RestController
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(Urls.Card.FULL)
    public ResponseEntity<String> saveOrUpdate(@RequestBody CardDto cardDto) {
        cardService.save(cardDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(Urls.Card.FULL)
    public List<CardDto> findAll() {
        return cardService.findAll();
    }

    @GetMapping(Urls.Card.Id.FULL)
    public ResponseEntity<CardDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @DeleteMapping(Urls.Card.Id.FULL)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        cardService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
