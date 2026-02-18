package com.yourname.training.javafundamentals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assurance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeAssurance;
    private String description;


    @ManyToMany(mappedBy = "assurances")
    private List<Client> clients = new ArrayList<>();
}