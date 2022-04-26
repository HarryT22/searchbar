package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersistenceTest {
    @Autowired
    RezepteRepository rezepteRepository;

    @Test
    public void findAllShouldWork(){
        List<Rezepte> rezepte = rezepteRepository.findAll("Fleisch");
        assertEquals(1, rezepte.get(0).getId());
        assertEquals(2, rezepte.get(1).getId());
        assertEquals(3, rezepte.get(2).getId());
        assertEquals(4, rezepte.get(3).getId());
        assertEquals(5, rezepte.get(4).getId());
        assertEquals(6, rezepte.get(5).getId());
    }
}
