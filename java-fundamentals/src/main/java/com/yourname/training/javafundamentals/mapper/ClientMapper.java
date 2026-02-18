package com.yourname.training.javafundamentals.mapper;


import com.yourname.training.javafundamentals.dto.ClientDTO;
import com.yourname.training.javafundamentals.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());           // OK for response DTO
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setDateNaissance(client.getDateNaissance());
        dto.setAdresse(client.getAdresse());
        dto.setTelephone(client.getTelephone());  // fixed: no need for String.valueOf() if telephone is already String
        dto.setEmail(client.getEmail());
        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        Client client = new Client();
        // Do NOT set the ID here when creating!
        // client.setId(dto.getId());   ← REMOVE or COMMENT this line

        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setDateNaissance(dto.getDateNaissance());
        client.setAdresse(dto.getAdresse());
        client.setTelephone(dto.getTelephone());
        client.setEmail(dto.getEmail());
        return client;
    }

    // Optional: if you also have an update method, create a separate one
    public static void updateEntityFromDto(Client client, ClientDTO dto) {
        // client.setId(...)   ← never touch ID
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setDateNaissance(dto.getDateNaissance());
        client.setAdresse(dto.getAdresse());
        client.setTelephone(dto.getTelephone());
        client.setEmail(dto.getEmail());
    }
}