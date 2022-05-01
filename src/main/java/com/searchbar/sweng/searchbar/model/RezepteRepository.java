package com.searchbar.sweng.searchbar.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface RezepteRepository {
    List<Rezepte> findByName(String name);
}
