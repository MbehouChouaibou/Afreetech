package com.yourname.training.javafundamentals.mapper;


import com.yourname.training.javafundamentals.dto.AssuranceDTO;
import com.yourname.training.javafundamentals.entity.Assurance;
import com.yourname.training.javafundamentals.entity.Client;

public class AssuranceMapper {

    public static AssuranceDTO toDTO(Assurance assurance) {
        AssuranceDTO dto = new AssuranceDTO();
        dto.setId(assurance.getId());
        dto.setTypeAssurance(assurance.getTypeAssurance());
        dto.setDescription(assurance.getDescription());

        // Optionnel : liste des client IDs
        if (assurance.getClients() != null) {
            dto.setClientIds(assurance.getClients().stream()
                    .map(Client::getId)
                    .toList());
        }
        return dto;
    }

    public static Assurance toEntity(AssuranceDTO dto) {
        Assurance assurance = new Assurance();
        assurance.setId(dto.getId());
        assurance.setTypeAssurance(dto.getTypeAssurance());
        assurance.setDescription(dto.getDescription());
        // Les clients ne sont pas settés ici → on les gère via addAssurance
        return assurance;
    }
}