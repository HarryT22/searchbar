package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.RezepteRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezepteJpaRepository extends CrudRepository<Rezepte,Integer>, RezepteRepository {
    @Query("SELECT r FROM Rezepte r WHERE r.name LIKE %?1% ORDER BY r.name")
    List<Rezepte> findAll(@Param("name")String name);
    List<Rezepte> findByName(String name);
    Optional<Rezepte> findById(int id);
}
