package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Food;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodJpaRepository {
    List<Food> findByName(String name);
}
