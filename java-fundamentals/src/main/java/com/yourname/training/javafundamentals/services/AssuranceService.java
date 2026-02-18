package com.yourname.training.javafundamentals.services;



import com.yourname.training.javafundamentals.dto.AssuranceDTO;
import com.yourname.training.javafundamentals.entity.Assurance;
import com.yourname.training.javafundamentals.mapper.AssuranceMapper;
import com.yourname.training.javafundamentals.repository.AssuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssuranceService {

    private final AssuranceRepository repository;


    public List<AssuranceDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(AssuranceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AssuranceDTO save(AssuranceDTO dto) {
        Assurance assurance = AssuranceMapper.toEntity(dto);
        return AssuranceMapper.toDTO(repository.save(assurance));
    }

    public AssuranceDTO update(Long id, AssuranceDTO dto) {
        Assurance existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assurance non trouvée"));

        // Mise à jour sélective (seulement les champs non null)
        if (dto.getTypeAssurance() != null) {
            existing.setTypeAssurance(dto.getTypeAssurance());
        }
        if (dto.getDescription() != null) {
            existing.setDescription(dto.getDescription());
        }

        return AssuranceMapper.toDTO(repository.save(existing));
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public AssuranceDTO getById(Long id) {
        Assurance assurance = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assurance non trouvée avec id: " + id));
        return AssuranceMapper.toDTO(assurance);
    }
}
