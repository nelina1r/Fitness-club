package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.service.interfaces.ClientService;

import java.util.List;

@Api(value = "Client controller")
@RestController
@RequestMapping("/api")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation("save client")
    @PostMapping("/client")
    public ResponseEntity<String> saveOrUpdate(@RequestBody ClientDto clientDto) {
        clientService.save(clientDto);
        return ResponseEntity.ok("ok");
    }

    @ApiOperation("add card by id to client by id")
    @PostMapping("/clientId/{clientId}/cardId/{cardId}")
    public ResponseEntity<String> addCardToClient(@PathVariable(value = "clientId") Long clientId,
                                                  @PathVariable(value = "cardId") Long cardId) {
        clientService.addCardToClient(cardId, clientId);
        return ResponseEntity.ok("now client with id = " + clientId + " has a card with id = " + cardId);
    }

    @ApiOperation("sign client by id to schedule by id")
    @PostMapping("/clientId/{clientId}/scheduleId/{scheduleId}")
    public ResponseEntity<String> signClientOnSchedule(@PathVariable(value = "clientId") Long clientId,
                                                       @PathVariable(value = "scheduleId") Long scheduleId) throws InvalidTypeException {
        clientService.signClientOnSchedule(clientId, scheduleId);
        return ResponseEntity.ok("now client with id = " + clientId + " signed on schedule with id = " + scheduleId);
    }

    @ApiOperation("find all clients")
    @GetMapping("/client")
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @ApiOperation("find client by id")
    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @ApiOperation("delete client by id")
    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}