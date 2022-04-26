package com.searchbar.sweng.searchbar.model;


import java.util.Optional;

public interface UnvertraeglichkeitenRepository {
    Optional<Unvertraeglichkeiten> findById(int id);
    Unvertraeglichkeiten save(Unvertraeglichkeiten u);
    void delete(Unvertraeglichkeiten u);
}
