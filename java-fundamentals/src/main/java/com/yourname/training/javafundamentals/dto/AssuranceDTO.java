package com.yourname.training.javafundamentals.dto;


import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AssuranceDTO {
    private Long id;
    private String typeAssurance;
    private String description;
    private List<Long> clientIds = new ArrayList<>();  // ou List<ClientDTO> si tu veux plus
}

