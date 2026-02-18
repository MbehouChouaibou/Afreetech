package com.yourname.training.javafundamentals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String adresse;
    private String telephone;
    private String email;

    // ── AJOUT ── ManyToMany bidirectionnel
    @ManyToMany
    @JoinTable(
            name = "client_assurance",                  // nom de la table de jointure auto-créée
            joinColumns = @JoinColumn(name = "client_id"),       // FK vers Client
            inverseJoinColumns = @JoinColumn(name = "assurance_id")  // FK vers Assurance
    )
    private List<Assurance> assurances = new ArrayList<>();

    // Méthodes utilitaires (très recommandé pour éviter bugs)
    public void addAssurance(Assurance assurance) {
        assurances.add(assurance);
        assurance.getClients().add(this);
    }

    public void removeAssurance(Assurance assurance) {
        assurances.remove(assurance);
        assurance.getClients().remove(this);
    }
}