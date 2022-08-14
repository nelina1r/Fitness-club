package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.model.entity.enums.Role;
import ru.t1.dedov.service.interfaces.CardService;

import java.util.List;
import java.util.Map;

@Api(value = "Card controller")
@RestController
@RequestMapping("/api")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation("save card")
    @PostMapping("/card")
    public ResponseEntity<String> saveOrUpdate(@RequestBody CardDto cardDto) {
        cardService.save(cardDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("find all cards")
    @GetMapping("/card")
    public List<CardDto> findAll() {
        return cardService.findAll();
    }

    @ApiOperation("fin card by id")
    @GetMapping("/card/{id}")
    public ResponseEntity<CardDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @ApiOperation("delete card by id")
    @DeleteMapping("/card/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        cardService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    //@Secured("ADMIN")
    @ApiOperation("add training type by id to card by id")
    @PostMapping("/cardId/{cardId}/TTid/{TTId}")
    public ResponseEntity<String> addTrainingTypeInCard(@PathVariable(value = "cardId") Long cardId,
                                                        @PathVariable(value = "TTId") Long TTId) {
        cardService.addTrainingTypeInCard(cardId, TTId);
        return ResponseEntity.ok("now card with id = " + cardId + " have a TT with id " + TTId);
    }
}
