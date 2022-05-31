package com.searchbar.sweng.searchbar.model.Repositories;

import com.searchbar.sweng.searchbar.model.Food;

import java.util.Optional;

public interface FoodRepository {
    Optional<Food> findById(int id);
}
