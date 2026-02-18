package com.yourname.training.javafundamentals.repository;


import com.yourname.training.javafundamentals.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Recherche par nom
    List<Client> findByNom(String nom);

    // Recherche par email
    Client findByEmail(String email);

    // Recherche par nom contenant une valeur
    List<Client> findByNomContaining(String keyword);
}

