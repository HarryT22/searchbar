package com.searchbar.sweng.searchbar.model;

import java.util.List;
import java.util.Optional;

public interface RezepteRepository {
    List<Rezepte> findByName(String name);
    List<Rezepte> findAll(String name);
    Optional<Rezepte> findById(int id);
    void delete(Rezepte r);
    Rezepte save(Rezepte r);
}
