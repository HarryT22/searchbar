package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodJpaRepository extends CrudRepository<Food,Integer> {
}
