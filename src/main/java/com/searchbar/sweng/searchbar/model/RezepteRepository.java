package com.searchbar.sweng.searchbar.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezepteRepository {
    List<Rezepte> findByRezeptName(String rezeptName);
    Optional<Rezepte> findByRezeptId(int id);
    void delete(Rezepte m);
    Rezepte save(Rezepte rezept);
}
