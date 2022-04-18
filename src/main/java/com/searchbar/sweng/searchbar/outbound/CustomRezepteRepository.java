package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Rezepte;

import java.util.List;

public interface CustomRezepteRepository {
    List<Rezepte> findAll(String name);
}
