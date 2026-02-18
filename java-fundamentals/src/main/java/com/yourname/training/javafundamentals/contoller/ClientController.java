package com.yourname.training.javafundamentals.contoller;

import com.yourname.training.javafundamentals.dto.ClientDTO;
import com.yourname.training.javafundamentals.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")  // À restreindre en production (ex: "http://localhost:5173")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

   


    @PostMapping("createclient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) {
        ClientDTO created = clientService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable Long id,
            @RequestBody ClientDTO dto) {
        ClientDTO updated = clientService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PostMapping("/{clientId}/assurances/{assuranceId}")
    public ResponseEntity<ClientDTO> associateAssuranceToClient(
            @PathVariable Long clientId,
            @PathVariable Long assuranceId) {
        ClientDTO updatedClient = clientService.associateAssuranceToClient(clientId, assuranceId);
        return ResponseEntity.ok(updatedClient);
    }


}