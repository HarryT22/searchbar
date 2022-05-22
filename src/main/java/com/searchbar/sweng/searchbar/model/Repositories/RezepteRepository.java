package com.searchbar.sweng.searchbar.model.Repositories;

import com.searchbar.sweng.searchbar.model.Rezepte;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface RezepteRepository {
    List<Rezepte> findByName(String name);
    Rezepte save(Rezepte r);
    Optional<Rezepte> findById(int id);
    void delete(Rezepte r);
    Rezepte findFirstByOrderByIdDesc();
}
