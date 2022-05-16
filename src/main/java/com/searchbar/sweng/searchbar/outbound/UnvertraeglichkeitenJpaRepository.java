package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Unvertraeglichkeiten;
import com.searchbar.sweng.searchbar.model.Repositories.UnvertraeglichkeitenRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnvertraeglichkeitenJpaRepository extends CrudRepository<Unvertraeglichkeiten,Integer>, UnvertraeglichkeitenRepository {
    Optional<Unvertraeglichkeiten> findById(int id);
}
