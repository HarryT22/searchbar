package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Rezepte;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezepteJpaRepository extends JpaRepository<Rezepte,Integer>,RezepteRepository {


    @Query("SELECT r FROM Rezepte r WHERE r.name LIKE %:name% ORDER BY r.name ASC")
    List<Rezepte> findByName(@Param("name") String name);

    Optional<Rezepte> findById(int id);

    Rezepte findFirstByOrderByIdDesc();

    Rezepte save(Rezepte r);

    void delete(Rezepte r);
}
