package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.*;
import com.searchbar.sweng.searchbar.model.Repositories.RezepteRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class PersistenceTest {
    @Autowired
    RezepteRepository rezepteRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Test
    public void findByNameShouldWork(){
        List<Rezepte> rezepte = rezepteRepository.findByName("Fleisch");
        assertEquals(1,rezepte.get(0).getId());
        assertEquals(2,rezepte.get(1).getId());
        assertEquals(3,rezepte.get(2).getId());
        assertEquals(4,rezepte.get(3).getId());
        assertEquals(5,rezepte.get(4).getId());
        assertEquals(6,rezepte.get(5).getId());
        List<Rezepte> rezepte2 = rezepteRepository.findByName("Bleisch");
        assert (rezepte2.isEmpty());
        List<Rezepte> rezepte3 = rezepteRepository.findByName("Test Fleisch");
        assert(rezepte3.isEmpty());
        List<Rezepte> rezepte4 = rezepteRepository.findByName("Fleisch");
        assert(rezepte4.size() == 6);
    }
}
