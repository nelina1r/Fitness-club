package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.CardDto;
import ru.t1.dedov.service.interfaces.CardService;

import java.util.List;

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
    public ResponseEntity<String> saveCard(@RequestBody CardDto cardDto) {
        cardService.save(cardDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("edit card")
    @PutMapping("/card/{id}")
    public ResponseEntity<String> editCard(@PathVariable Long id, @RequestBody CardDto cardDto) {
        cardService.editById(id, cardDto);
        return ResponseEntity.ok("updated");
    }

    @ApiOperation("find all cards")
    @GetMapping("/card")
    public List<CardDto> findAllCards(
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault Pageable page
    ) {
        return cardService.findAll(search, page);
    }

    @ApiOperation("find card by id")
    @GetMapping("/card/{id}")
    public ResponseEntity<CardDto> findCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @ApiOperation("delete card by id")
    @DeleteMapping("/card/{id}")
    public ResponseEntity<String> deleteCardById(@PathVariable Long id) {
        cardService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    //@Secured("GUEST")
    @ApiOperation("add training type by id to card by id")
    @PostMapping("/cardId/{cardId}/TTid/{TTId}")
    public ResponseEntity<String> addTrainingTypeInCard(@PathVariable(value = "cardId") Long cardId,
                                                        @PathVariable(value = "TTId") Long TTId) {
        cardService.addTrainingTypeInCard(cardId, TTId);
        return ResponseEntity.ok("now card with id = " + cardId + " have a TT with id " + TTId);
    }
}
