package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RezepteJpaRepository extends JpaRepository<Rezepte,Integer>,RezepteRepository {
    List<Rezepte> findByName(String name);
}
