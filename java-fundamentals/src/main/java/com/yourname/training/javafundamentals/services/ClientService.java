package com.yourname.training.javafundamentals.services;

import com.yourname.training.javafundamentals.dto.ClientDTO;
import com.yourname.training.javafundamentals.entity.Assurance;
import com.yourname.training.javafundamentals.entity.Client;
import com.yourname.training.javafundamentals.mapper.ClientMapper;
import com.yourname.training.javafundamentals.repository.AssuranceRepository;
import com.yourname.training.javafundamentals.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final AssuranceRepository assuranceRepository;
    private final ClientMapper clientMapper;

    public List<ClientDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClientDTO save(ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);
        Client saved = repository.save(client);
        return clientMapper.toDTO(saved);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + id));

        // Use the mapper's update method (safer and cleaner)
        ClientMapper.updateEntityFromDto(existing, dto);

        Client saved = repository.save(existing);
        return clientMapper.toDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'id : " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public ClientDTO associateAssuranceToClient(Long clientId, Long assuranceId) {
        Client client = repository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id : " + clientId));

        Assurance assurance = assuranceRepository.findById(assuranceId)
                .orElseThrow(() -> new RuntimeException("Assurance non trouvée avec l'id : " + assuranceId));

        // Prevent duplicate association
        if (client.getAssurances().contains(assurance)) {
            throw new RuntimeException("Cette assurance est déjà associée à ce client");
        }

        client.addAssurance(assurance);

        Client updated = repository.save(client);
        return clientMapper.toDTO(updated);
    }
    @Transactional
    public ClientDTO createClient(ClientDTO dto) {
        // Validation minimale (optionnelle mais recommandée)
        if (dto.getNom() == null || dto.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email est obligatoire");
        }

        // Conversion DTO → Entity
        Client client = clientMapper.toEntity(dto);

        // Sauvegarde
        Client saved = repository.save(client);

        // Retour du DTO mis à jour (avec ID généré)
        return clientMapper.toDTO(saved);
    }
}