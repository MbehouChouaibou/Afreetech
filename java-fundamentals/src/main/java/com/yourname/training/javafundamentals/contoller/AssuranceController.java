package com.yourname.training.javafundamentals.contoller;

import com.yourname.training.javafundamentals.dto.AssuranceDTO;
import com.yourname.training.javafundamentals.services.AssuranceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assurances")   // base path cohérent avec REST
@AllArgsConstructor
public class AssuranceController {

    private final AssuranceService assuranceService;

    // GET /api/assurances → liste toutes les assurances
    @GetMapping
    public ResponseEntity<List<AssuranceDTO>> getAllAssurances() {
        List<AssuranceDTO> assurances = assuranceService.getAll();
        return ResponseEntity.ok(assurances);
    }

    // GET /api/assurances/{id} → une assurance par ID
    @GetMapping("/{id}")
    public ResponseEntity<AssuranceDTO> getAssuranceById(@PathVariable Long id) {
        return ResponseEntity.ok(assuranceService.getById(id));
    }
    // POST /api/assurances → créer une nouvelle assurance
    @PostMapping
    public ResponseEntity<AssuranceDTO> createAssurance(@RequestBody AssuranceDTO dto) {
        AssuranceDTO created = assuranceService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/assurances/{id} → modifier une assurance
    @PutMapping("/{id}")
    public ResponseEntity<AssuranceDTO> updateAssurance(
            @PathVariable Long id,
            @RequestBody AssuranceDTO dto) {
        AssuranceDTO updated = assuranceService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/assurances/{id} → supprimer une assurance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable Long id) {
        assuranceService.delete(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

}