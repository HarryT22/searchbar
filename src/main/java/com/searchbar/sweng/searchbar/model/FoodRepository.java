package com.searchbar.sweng.searchbar.model;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    Optional<Food> findById(Integer id);
    List<Food> findByName(String name);
    Food save(Food f);
    void delete(Food f);
}