package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.service.interfaces.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public ResponseEntity<String> saveOrUpdate(@RequestBody ClientDto clientDto) {
        clientService.save(clientDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/client")
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}