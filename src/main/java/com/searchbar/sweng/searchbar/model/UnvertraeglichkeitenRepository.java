package com.searchbar.sweng.searchbar.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnvertraeglichkeitenRepository {
    List<Unvertraeglichkeiten> findById(int id);
    Unvertraeglichkeiten save(Unvertraeglichkeiten u);
    void delete(Unvertraeglichkeiten u);
}
