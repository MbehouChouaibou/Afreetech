package com.yourname.training.javafundamentals.repository;


import com.yourname.training.javafundamentals.entity.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {

    // Recherche par type d'assurance
    List<Assurance> findByTypeAssurance(String typeAssurance);

    // Recherche contenant un mot
    List<Assurance> findByTypeAssuranceContaining(String keyword);
}
