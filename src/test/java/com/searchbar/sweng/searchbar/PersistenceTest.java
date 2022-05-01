package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
public class PersistenceTest {
    @Autowired
    RezepteRepository rezepteRepository;

    @Test
    public void findByNameShouldWork(){
        String test = "Fleisch";
        List<Rezepte> rezepte = rezepteRepository.findByName(test);
        Rezepte rez = rezepte.get(0);
        assert(rez.getId()==1);
        Rezepte rez2 = rezepte.get(1);
        assert(rez2.getId()==2);
        List<Rezepte> rez3 = rezepteRepository.findByName(test);
        assert(rez3.isEmpty());
    }
}
