package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezepteJpaRepository extends CrudRepository<Rezepte,Integer>, RezepteRepository {
    List<Rezepte> findByName(String name);
    Optional<Rezepte> findById(int id);
}